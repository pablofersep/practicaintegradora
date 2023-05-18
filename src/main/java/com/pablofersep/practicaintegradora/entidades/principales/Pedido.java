package com.pablofersep.practicaintegradora.entidades.principales;

import com.pablofersep.practicaintegradora.entidades.auxiliares.LineaPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Pedido {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fecha_realizacion")
    private LocalDate fechaRealizacion;
    @ElementCollection
    private Set<LineaPedido> lineasPedido;
    @Column(name = "precio_total")
    private BigDecimal precioTotal;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_cliente_pedido"))
    private Cliente cliente;
    @Column(name = "estado_pedido")
    private String estadoPedido;
    @ManyToOne
    @JoinColumn(name = "usuario_admin_procesa", foreignKey = @ForeignKey(name = "FK_admin_procesa_pedido"))
    private Usuario usuarioAdminProcesa;

}
