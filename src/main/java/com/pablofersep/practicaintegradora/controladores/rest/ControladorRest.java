package com.pablofersep.practicaintegradora.controladores.rest;

import com.pablofersep.practicaintegradora.controladores.ControladorAviso;
import com.pablofersep.practicaintegradora.entidades.auxiliares.LineaCarrito;
import com.pablofersep.practicaintegradora.entidades.auxiliares.LineaPedido;
import com.pablofersep.practicaintegradora.entidades.principales.*;
import com.pablofersep.practicaintegradora.formobjects.EmailDTO;
import com.pablofersep.practicaintegradora.formobjects.LineaCarritoDTO;
import com.pablofersep.practicaintegradora.servicios.datos.EstadoPedidoService;
import com.pablofersep.practicaintegradora.servicios.datos.UrgenciaAvisoService;
import com.pablofersep.practicaintegradora.servicios.principales.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/rest")
public class ControladorRest {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private CarritoService carritoService;
    @Autowired
    private AvisoService avisoService;
    @Autowired
    private UrgenciaAvisoService urgenciaAvisoService;
    @Autowired
    private EstadoPedidoService estadoPedidoService;
    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/recuperarClave")
    public String recuperarClave(@RequestParam String correo) {
        Usuario usuario = usuarioService.findByEmailEquals(correo);
        return usuario.getClave();
    }

    @CrossOrigin
    @GetMapping("/producto/devolver_todos")
    public ResponseEntity<Iterable<Producto>> devolverProductos() {
        return ResponseEntity.ok(productoService.findAll());
    }
    @CrossOrigin
    @GetMapping("/producto/categoria/{categoria}")
    public ResponseEntity<Iterable<Producto>> devolverProductosbycat(
            @PathVariable("categoria")String cat
    ) {
        return ResponseEntity.ok(productoService.findAllByCat(cat));
    }
    @CrossOrigin
    @GetMapping("/producto/{id}")
    public ResponseEntity<Producto> devolverProducto(
            @PathVariable("id")String id
    ) {
        return ResponseEntity.ok(productoService.findProductoByCodigo(id));
    }

    @CrossOrigin
    @PutMapping("/carrito/comprar")
    public boolean compraCarrito(
            @RequestBody EmailDTO email
    ){
        Carrito carrito = carritoService.findCarritoByEmailCliente(email.getEmail());
        Pedido pedido = new Pedido();
        Cliente cliente = carrito.getCliente();
        cliente.setGastoAcumuladoCliente(cliente.getGastoAcumuladoCliente().add(carrito.getPrecio()));

        pedido.setCliente(cliente);
        pedido.setPrecioTotal(carrito.getPrecio());
        pedido.setFechaRealizacion(LocalDate.now());
        pedido.setEstadoPedido(estadoPedidoService.findBySiglas("P").getNombre());

        Producto producto;
        for (LineaCarrito lc : carrito.getLineasCarrito()){
            producto = lc.getProducto();
            int i = producto.getCantidadAlmacen()- lc.getUnidades();
            //if (i < 0) return false;
            producto.setCantidadAlmacen(i);
            Producto comprobacion = productoService.crear_modificar(producto);
            if (comprobacion == null) return false;
            ControladorAviso.creacionAviso(comprobacion,productoService, urgenciaAvisoService, avisoService);
            pedido.getLineasPedido().add(new LineaPedido(producto, lc.getUnidades(), producto.getPrecio()));
        }
        carrito.getLineasCarrito().clear();
        carrito.setPrecio(BigDecimal.valueOf(0));
        Carrito comprobacion = carritoService.crear_modificar(carrito);
        if (comprobacion == null) return false;
        Pedido comprobacion1 = pedidoService.crear_modificar(pedido);
        if (comprobacion1 == null) return false;
        return true;
    }
    @CrossOrigin
    @PostMapping("/carrito/listar")
    public ResponseEntity<Iterable<LineaCarrito>> devolverCarrito(
            @RequestBody EmailDTO email
    ){
        return ResponseEntity.ok(carritoService.findCarritoByEmailCliente(email.getEmail()).getLineasCarrito());
    }
    @CrossOrigin
    @PutMapping("/carrito/vaciar")
    public boolean vaciarCarrito(
            @RequestBody EmailDTO email
    ){
        Carrito carrito = carritoService.findCarritoByEmailCliente(email.getEmail());
        carrito.getLineasCarrito().clear();
        Carrito comprobacion = carritoService.crear_modificar(carrito);
        if (comprobacion == null){
            return false;
        }
        return true;
    }
    @CrossOrigin
    @PostMapping("/carrito/linea/insert")
    public ResponseEntity<Boolean> crearLinea(
            @RequestBody LineaCarritoDTO lineaCarritoDTO
    ) {
        LineaCarrito lc = new LineaCarrito();
        Producto producto = productoService.findProductoByCodigo(lineaCarritoDTO.getId());
        lc.setProducto(producto);
        Carrito carrito = carritoService.findCarritoByEmailCliente(lineaCarritoDTO.getEmail());
        for (LineaCarrito linea : carrito.getLineasCarrito()) {
            if (linea.getProducto().getCodigo().equals(producto.getCodigo())) {
                return ResponseEntity.ok(false);
            }
        }
        lc.setUnidades(1);
        carrito.getLineasCarrito().add(lc);
        float precio = 0;
        for (LineaCarrito linea : carrito.getLineasCarrito()) {
            precio += linea.getProducto().getPrecio().floatValue() * linea.getUnidades();
        }
        carrito.setPrecio(BigDecimal.valueOf(precio));
        Carrito comprobacion = carritoService.crear_modificar(carrito);
        if (comprobacion == null) {
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(true);
    }
    @CrossOrigin
    @PutMapping("/carrito/linea/sumar")
    public boolean sumarUnidadLinea(
            @RequestBody LineaCarritoDTO lineaCarritoDTO
    ) {
        Producto producto = productoService.findProductoByCodigo(lineaCarritoDTO.getId());
        Carrito carrito = carritoService.findCarritoByEmailCliente(lineaCarritoDTO.getEmail());
        //Condicion para decir que no nos quedan
        for (LineaCarrito linea : carrito.getLineasCarrito()) {
            if (linea.getProducto().getCodigo().equals(producto.getCodigo())) {
                linea.setUnidades(linea.getUnidades()+1);
            }
        }
        float precio = 0;
        for (LineaCarrito linea : carrito.getLineasCarrito()) {
            precio += linea.getProducto().getPrecio().floatValue() * linea.getUnidades();
        }
        carrito.setPrecio(BigDecimal.valueOf(precio));
        Carrito comprobacion = carritoService.crear_modificar(carrito);
        if (comprobacion == null) {
            return false;
        }
        return true;
    }
    @CrossOrigin
    @PutMapping("/carrito/linea/restar")
    public boolean restarUnidadLinea(
            @RequestBody LineaCarritoDTO lineaCarritoDTO
    ) {
        Producto producto = productoService.findProductoByCodigo(lineaCarritoDTO.getId());
        Carrito carrito = carritoService.findCarritoByEmailCliente(lineaCarritoDTO.getEmail());
        for (LineaCarrito linea : carrito.getLineasCarrito()) {
            if (linea.getProducto().getCodigo().equals(producto.getCodigo())) {
                linea.setUnidades(linea.getUnidades()-1);
            }
        }
        float precio = 0;
        for (LineaCarrito linea : carrito.getLineasCarrito()) {
            precio += linea.getProducto().getPrecio().floatValue() * linea.getUnidades();
        }
        carrito.setPrecio(BigDecimal.valueOf(precio));
        Carrito comprobacion = carritoService.crear_modificar(carrito);
        if (comprobacion == null) {
            return false;
        }
        return true;
    }
    @CrossOrigin
    @PutMapping("/carrito/linea/delete")
    public boolean borrarLinea(
            @RequestBody LineaCarritoDTO lineaCarritoDTO
    ) {
        LineaCarrito lc = new LineaCarrito();
        Producto producto = productoService.findProductoByCodigo(lineaCarritoDTO.getId());
        lc.setProducto(producto);
        lc.setUnidades(lineaCarritoDTO.getUnidades());
        Carrito carrito = carritoService.findCarritoByEmailCliente(lineaCarritoDTO.getEmail());
        System.out.println(carrito.getLineasCarrito().remove(lc));
        float precio = 0;
        for (LineaCarrito linea : carrito.getLineasCarrito()) {
            precio += linea.getProducto().getPrecio().floatValue() * linea.getUnidades();
        }
        carrito.setPrecio(BigDecimal.valueOf(precio));
        Carrito comprobacion = carritoService.crear_modificar(carrito);
        if (comprobacion == null) {
            return false;
        }
        return true;
    }
}
