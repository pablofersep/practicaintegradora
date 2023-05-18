drop schema practicaintegradora;
create schema practicaintegradora;

insert into usuario(email, clave, numero_accesos) values ("alfa@gmail.com","alfa", 0);
insert into usuario(email, clave, numero_accesos) values ("beta@gmail.com","beta", 0);
insert into usuario(email, clave, numero_accesos) values ("ganma@gmail.com","ganma", 0);

insert into genero(nombre, siglas) values ("Femenino", "F");
insert into genero(nombre, siglas) values ("Masculino", "M");
insert into genero(nombre, siglas) values ("No Binario", "N");
insert into genero(nombre, siglas) values ("Otro", "O");
insert into tipo_documento_cliente(nombre, siglas) values ("DNI", "D");
insert into tipo_documento_cliente(nombre, siglas) values ("NIE", "N");
insert into tipo_documento_cliente(nombre, siglas) values ("Nº Pasaporte", "P");
insert into tipo_documento_cliente(nombre, siglas) values ("Nº Seguridad Social", "S");
insert into tipo_documento_proveedor(nombre, siglas) values ("CIF", "C");
insert into tipo_documento_proveedor(nombre, siglas) values ("DNI", "D");
insert into tipo_via(nombre, siglas) values ("Avenida", "Av");
insert into tipo_via(nombre, siglas) values ("Calle", "Cl");
insert into tipo_via(nombre, siglas) values ("Glorieta", "Gl");
insert into tipo_via(nombre, siglas) values ("Paseo", "Ps");
insert into tipo_via(nombre, siglas) values ("Plaza", "Pz");
insert into tipo_cliente(nombre, siglas, gasto_umbral, porcentaje_descuento) values ("Ninguno", "Na", 0, 0);
insert into tipo_cliente(nombre, siglas, gasto_umbral, porcentaje_descuento) values ("Bronce", "Br", 100, 5);
insert into tipo_cliente(nombre, siglas, gasto_umbral, porcentaje_descuento) values ("Plata", "Pl", 200, 10);
insert into tipo_cliente(nombre, siglas, gasto_umbral, porcentaje_descuento) values ("Oro", "O", 300, 15);
insert into tipo_cliente(nombre, siglas, gasto_umbral, porcentaje_descuento) values ("Platino", "Pt", 400, 20);
insert into clase_proveedor(nombre, siglas) values ("Básico", "B");
insert into clase_proveedor(nombre, siglas) values ("Prioritario", "P");
insert into clase_proveedor(nombre, siglas) values ("Esencial", "E");
insert into tipo_tarjeta_credito(nombre, siglas) values ("Master Card", "M");
insert into tipo_tarjeta_credito(nombre, siglas) values ("Visa", "V");
insert into tipo_tarjeta_credito(nombre, siglas) values ("American Express", "A");
insert into tamanio_imagen(nombre, siglas) values ("Pequeña", "P");
insert into tamanio_imagen(nombre, siglas) values ("Mediana", "M");
insert into tamanio_imagen(nombre, siglas) values ("Grande", "L");
insert into tamanio_imagen(nombre, siglas) values ("Extra Grande", "XL");
insert into formato_imagen(nombre, siglas) values ("gif", "g");
insert into formato_imagen(nombre, siglas) values ("png", "p");
insert into formato_imagen(nombre, siglas) values ("jpg", "j");
insert into formato_imagen(nombre, siglas) values ("svg", "s");
insert into urgencia_aviso(nombre, siglas) values ("Baja", "B");
insert into urgencia_aviso(nombre, siglas) values ("Media", "M");
insert into urgencia_aviso(nombre, siglas) values ("Alta", "A");
insert into punto_cardinal(nombre, siglas) values ("Norte", "N");
insert into punto_cardinal(nombre, siglas) values ("Sur", "S");
insert into punto_cardinal(nombre, siglas) values ("Este", "E");
insert into punto_cardinal(nombre, siglas) values ("Oeste", "O");
insert into pais(nombre, siglas) values ("España", "es");
insert into pais(nombre, siglas) values ("Francia", "fr");
insert into pais(nombre, siglas) values ("Italia", "it");
insert into pais(nombre, siglas) values ("Portugal", "pt");
insert into idioma(nombre, siglas) values ("Inglés", "en");
insert into idioma(nombre, siglas) values ("Español", "es");
insert into idioma(nombre, siglas) values ("Francés", "fr");


insert into direccion(codigo_postal, localidad, nombre, numero, planta, portal, puerta, region, tipo_via) values ("28030", "Madrid", "Arroyo Fontarron", 393, "3", "", "c", "Madrid", 3);
insert into direccion(codigo_postal, localidad, nombre, numero, planta, portal, puerta, region, tipo_via) values ("28002", "Madrid", "Garcia Luna", 25, "Bajo", "", "c", "Madrid", 3);

delete from cliente where id_usuario like "beta@gmail.com";
insert into cliente(id_usuario, nombre, apellidos, comentarios, aceptacion_licencia, documento, tipo_documento_cliente, genero, pais_nacimiento, telefono_movil, gasto_acumulado_cliente, fecha_nacimiento, tipo_cliente) 
values ("alfa@gmail.com", "Pablo", "Fernandez", "Coment", true, "si", "DNI", "Macho", "es", "123456789", 300, "2001-11-12", "Bronce");
insert into cliente(id_usuario, nombre, apellidos, comentarios, aceptacion_licencia, documento, tipo_documento_cliente, genero, pais_nacimiento, telefono_movil, gasto_acumulado_cliente, direccion, fecha_nacimiento, tipo_cliente) 
values ("beta@gmail.com", "Kike", "Tort", "Si", false, "45789032F", "DNI", "Macho", 2, "987654321", 13, 2, "2001-12-09", "Bronce");


insert into categoria(codigo, descripcion) values ("1", "deporte");
insert into categoria(codigo, descripcion) values ("2", "musica");
insert into categoria(codigo, descripcion) values ("3", "cine");
insert into categoria(codigo, descripcion, categoria_padre) values ("4", "futbol", "1");
insert into categoria(codigo, descripcion, categoria_padre) values ("5", "pop", "2");
insert into categoria(codigo, descripcion, categoria_padre) values ("6", "fantasia", "3");
insert into categoria(codigo, descripcion, categoria_padre) values ("7", "futbol europeo", "4");
insert into categoria(codigo, descripcion, categoria_padre) values ("8", "steampunk", "6");
insert into categoria_categorias_hijas values ("1", "4");
insert into categoria_categorias_hijas values ("2", "5");
insert into categoria_categorias_hijas values ("3", "6");
insert into categoria_categorias_hijas values ("3", "7");
insert into categoria_categorias_hijas values ("4", "7");
insert into categoria_categorias_hijas values ("5", "8");

insert into producto(codigo, cantidad_almacen, comentarios, descripcion, descuento, en_oferta, es_novedad, gasto_acumulado, marca, modelo, precio, umbral_oculto_tienda, umbral_solicitud_proveedor, unidades_venedidas, valoracion_producto)
values("1", 100, "Es una katana", "Uchigatana", 0.0, false, true, 150.50, "Dark", "Souls", 75.25, 3, 6, 2, 5);
insert into producto_categorias(producto_codigo, categorias_codigo) values ("1", "3");
