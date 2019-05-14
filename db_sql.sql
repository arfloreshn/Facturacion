CREATE DATABASE `ventas` /*!40100 DEFAULT CHARACTER SET latin1 */;


CREATE TABLE `clientes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(80) NOT NULL,
  `apellidos` varchar(80) NOT NULL,
  `direccion` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE `facdet` (
  `id_row` int(11) NOT NULL AUTO_INCREMENT,
  `idfactura` int(11) NOT NULL,
  `nro_item` int(11) NOT NULL,
  `codproducto` varchar(20) NOT NULL,
  `cantidad` double NOT NULL,
  `preciovta` double NOT NULL,
  `subtotal` double NOT NULL,
  PRIMARY KEY (`id_row`),
  KEY `FK_facdet_idx` (`idfactura`),
  KEY `FK_productos_idx` (`codproducto`),
  CONSTRAINT `FK_facmae` FOREIGN KEY (`idfactura`) REFERENCES `facmae` (`idfactura`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_productos` FOREIGN KEY (`codproducto`) REFERENCES `producto` (`codproducto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

CREATE TABLE `facmae` (
  `idfactura` int(11) NOT NULL AUTO_INCREMENT,
  `nro_factura` int(11) NOT NULL,
  `fecfactura` datetime NOT NULL,
  `idcliente` int(11) NOT NULL DEFAULT '0',
  `subtotal` decimal(18,4) NOT NULL DEFAULT '0.0000',
  `iva` decimal(18,4) NOT NULL DEFAULT '0.0000',
  `totalventa` decimal(18,4) NOT NULL DEFAULT '0.0000',
  PRIMARY KEY (`idfactura`),
  KEY `FK_Cliente_idx` (`idcliente`),
  CONSTRAINT `FK_Cliente` FOREIGN KEY (`idcliente`) REFERENCES `clientes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

CREATE TABLE `mae_plan_wkf` (
  `id_plan` int(11) NOT NULL,
  `fec_plan` int(11) NOT NULL,
  `tot_emp` int(11) DEFAULT NULL,
  `id_bco` int(11) NOT NULL,
  `id_cta_bancaria` int(11) NOT NULL,
  `creada_por` varchar(20) NOT NULL,
  `fec_creacion` date NOT NULL,
  `sn_revisada` tinyint(4) NOT NULL,
  `fec_revisa` date NOT NULL,
  `revisada_por` varchar(20) DEFAULT NULL,
  `sn_autorizada` tinyint(4) NOT NULL,
  `fec_autorizada` date NOT NULL,
  `autorizada_por` varchar(20) DEFAULT NULL,
  `id_moneda` tinyint(4) NOT NULL,
  `factor_cambio` decimal(6,4) NOT NULL,
  `nro_cheque` varchar(20) NOT NULL,
  `imp_cheque` decimal(18,2) NOT NULL,
  `imp_cheque_eq` decimal(18,2) NOT NULL,
  `sn_enviada` tinyint(4) NOT NULL,
  `fec_enviada` date NOT NULL,
  `enviada_por` varchar(20) DEFAULT NULL,
  `sn_contabilizada` tinyint(4) NOT NULL,
  `fec_contabilizacion` date NOT NULL,
  `contabilizada_por` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_menu` tinyint(4) DEFAULT NULL,
  `id_item` int(11) DEFAULT NULL,
  `nombre` varchar(50) NOT NULL,
  `id_nivel` int(11) DEFAULT NULL,
  `tipo_item` char(1) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `icono` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;

CREATE TABLE `menurol` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_rol` int(11) NOT NULL,
  `id_menu` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_menurol_id_rol` (`id_rol`),
  KEY `id_menu` (`id_menu`),
  CONSTRAINT `fk_menurol_id_rol` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

CREATE TABLE `producto` (
  `codproducto` varchar(20) NOT NULL,
  `nomproducto` varchar(80) NOT NULL,
  `costo_unitario` double NOT NULL DEFAULT '0',
  `precio_venta` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`codproducto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `rol` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

CREATE TABLE `tcargo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom_cargo` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=329 DEFAULT CHARSET=latin1;

CREATE TABLE `tcontacto_empleado` (
  `id` int(11) NOT NULL,
  `id_contacto` int(11) DEFAULT NULL,
  `id_empleado` int(11) DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `tcorrelativo` (
  `tabla` varchar(50) NOT NULL DEFAULT '0',
  `ult_nro` smallint(6) NOT NULL DEFAULT '0',
  PRIMARY KEY (`tabla`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `tcta_bco_empleado` (
  `nro_cuenta` varchar(20) NOT NULL,
  `sn_activa` tinyint(4) NOT NULL DEFAULT '-1',
  `id_empleado` int(11) NOT NULL,
  PRIMARY KEY (`nro_cuenta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `tdepto_emp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom_depto` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

CREATE TABLE `tdepto_pais` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_pais` int(11) NOT NULL,
  `nom_depto` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_tpais_id` (`id_pais`),
  CONSTRAINT `fk_tpais_id` FOREIGN KEY (`id_pais`) REFERENCES `tpais` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

CREATE TABLE `templeados` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cod_empleado` varchar(20) NOT NULL DEFAULT '0',
  `primer_apellido` varchar(50) NOT NULL DEFAULT '0',
  `segundo_apellido` varchar(50) DEFAULT '0',
  `nombres` varchar(80) NOT NULL DEFAULT '0',
  `nro_identidad` varchar(20) NOT NULL,
  `fecha_nac` date NOT NULL,
  `lugar_nacimiento` varchar(60) NOT NULL,
  `sexo` char(1) NOT NULL,
  `estado_civil` char(1) NOT NULL,
  `sueldo_bruto` decimal(16,2) NOT NULL,
  `fec_alta` date NOT NULL,
  `fec_baja` date DEFAULT NULL,
  `tipo_empleado` char(1) NOT NULL,
  `creado_por` varchar(20) NOT NULL,
  `modificado_por` varchar(20) DEFAULT NULL,
  `fec_modificado` datetime DEFAULT NULL,
  `id_pais` int(11) NOT NULL,
  `id_depto_pais` int(11) NOT NULL,
  `id_municipio` int(11) NOT NULL,
  `id_cargo` int(11) NOT NULL,
  `id_profesion` int(11) NOT NULL,
  `id_depto_emp` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nro_identidad` (`nro_identidad`),
  KEY `fk_tpais_id_pais_emp` (`id_pais`),
  KEY `fk_tdepto_pais_id` (`id_depto_pais`),
  KEY `fk_tmunicipio_id_depto_id_pais` (`id_municipio`),
  KEY `fk_tcargo_id` (`id_cargo`),
  KEY `fk_tprofesion_id` (`id_profesion`),
  CONSTRAINT `fk_tcargo_id` FOREIGN KEY (`id_cargo`) REFERENCES `tcargo` (`id`),
  CONSTRAINT `fk_tdepto_pais_id` FOREIGN KEY (`id_depto_pais`) REFERENCES `tdepto_pais` (`id`),
  CONSTRAINT `fk_tmunicipio_id_depto_id_pais` FOREIGN KEY (`id_municipio`) REFERENCES `tmunicipios` (`id`),
  CONSTRAINT `fk_tprofesion_id` FOREIGN KEY (`id_profesion`) REFERENCES `tprofesion` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE `testado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `desc` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

CREATE TABLE `tmov_empleado` (
  `id` int(11) NOT NULL,
  `id_movimiento` int(11) NOT NULL,
  `id_empleado` int(11) NOT NULL,
  `vlr_movimiento` decimal(16,6) NOT NULL,
  `sn_activa` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tmovimiento_id` (`id_movimiento`),
  KEY `fk_templeado_id` (`id_empleado`),
  CONSTRAINT `fk_templeado_id` FOREIGN KEY (`id_empleado`) REFERENCES `templeados` (`id`),
  CONSTRAINT `fk_tmovimiento_id` FOREIGN KEY (`id_movimiento`) REFERENCES `tmovimientos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `tmovimientos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(60) NOT NULL,
  `tipo` char(1) NOT NULL,
  `valor` decimal(12,4) NOT NULL,
  `id_operacion` int(11) NOT NULL,
  `sn_activa` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_toperacion_id` (`id_operacion`),
  CONSTRAINT `fk_toperacion_id` FOREIGN KEY (`id_operacion`) REFERENCES `toperacion` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

CREATE TABLE `tmunicipios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_pais` int(11) NOT NULL,
  `id_depto_pais` int(11) NOT NULL,
  `nom_muni` varchar(50) NOT NULL,
  `cod_municipio` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cod_municipio` (`cod_municipio`),
  KEY `fk_muni_id_pais` (`id_pais`),
  KEY `fk_muni_id_depto_pais_id` (`id_depto_pais`),
  CONSTRAINT `fk_muni_id_depto_pais_id` FOREIGN KEY (`id_depto_pais`) REFERENCES `tdepto_pais` (`id`),
  CONSTRAINT `fk_muni_id_pais` FOREIGN KEY (`id_pais`) REFERENCES `tpais` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=299 DEFAULT CHARSET=latin1;

CREATE TABLE `toperacion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

CREATE TABLE `tpais` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom_pais` varchar(120) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=244 DEFAULT CHARSET=latin1;

CREATE TABLE `tprofesion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom_profesion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=latin1;

CREATE TABLE `users` (
  `user_name` varchar(20) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_rol` int(11) NOT NULL,
  `usuario` varchar(30) NOT NULL,
  `clave` varchar(30) NOT NULL,
  `correo` varchar(50) DEFAULT NULL,
  `estado` tinyint(4) DEFAULT NULL,
  `usuariocreado` varchar(20) DEFAULT NULL,
  `fechacreacion` datetime DEFAULT NULL,
  `usuariomodifica` varchar(20) DEFAULT NULL,
  `fechamodifica` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_rol_id_rol` (`id_rol`),
  CONSTRAINT `fk_rol_id_rol` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
.users;