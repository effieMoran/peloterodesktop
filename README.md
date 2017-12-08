# Pelotero Desktop 

Sistema Universitario para la Cátedra "Metodología de sistemas"

##Escenario

Se debe desarrollar el sistema para registrar las operaciones que se realizan en el complejo infantil Travesuras
Infantiles ubicado en la ciudad de Resistencia que se dedica a la organización de eventos infantiles. Se cuenta con la
siguiente información:

1. Las fiestas que se ofrecen son de tipo temática. Hay un listado de temas disponible para los clientes.
2. Hay tres combos propuestos:
    - Combo 1: $ 10000
        - Torta de 3 kilos
        - Decoración
        - Invitaciones
        - Bebida
        - Comida: golosinas, galletas y chizitos
        - Pelotero
        - Piñata
    - Combo 2:$ 15000
        - Torta de 3 kilos
        - Decoración
        - Invitaciones
        - Bebida
        - Comida: golosinas, galletas chizitos y panchitos
        - 2 Peloteros
        - Mesa dulce
        - Animador
        - Piñata
    - Combo 3:$ 20000
        - Torta de 3 kilos
        - Decoración
        - Invitaciones
        - Bebida
        - Comida: golosinas, galletas ,chizitos, panchitos y pizzetas.
        - 2 Peloteros
        - Animador
        - Títeres
        - Magia
        - Mesa Dulce
        - Piñata
        - Bolsitas
3. Cualquier combo es para 15 invitados infantiles. En caso de ser  más niños infantiles se cobra $ 100 por cada uno que
se agrega.
4. En el caso del servicio para de catering para adultos este debe ser provisto por la empresa o por los padres. En el
caso de ser provisto lo los padres, las bebidas se deben adquirir en el local. En caso contrario la empresa cuenta con
servicio de bar. El salón tiene capacidad para 20 adultos.
5. La duración de cada fiesta es de 3 horas, por día se pueden pedir hasta dos turnos, uno a las 14.00 hs y otro a
partir de las 18.00 hs.
6. Para  hacer una reserva se debe realizar un pago del 10% del combo seleccionado. En caso de cancelación no hay
reembolso. El pago completo del servicio debe hacerse  hasta 72 hs.  hasta las 17.00 hs.antes del evento en caso de no
hacerlo se produce la cancelación automática del servicio.
7. Las reservas se pueden hacer hasta 30 días antes como máximo.
8. El servicio de video y fotografía es opcional:
    - Servicio de foto:$ 1000
    - Servicio de Video: $ 1500
    - Servicio de Video y Foto: $ 2000
9. Los pagos se pueden realizar en efectivo, tarjeta de crédito o débito.
10. Al final del día la encargada de Administración debe entregar al dueño:
11. Listado de reservas.
12. Listado de servicios para el día siguiente.
13. Total de ingresos, por servicio y detallar los ítems por servicio.
14. Stock del bar.

El sistema deberá:
- Verificar el ingreso del responsable al sistema (permisos, considerar un administrador y un usuario común).
- Listado de clientes.
- Listado de reservas por clientes.
- Listado de espera de los clientes.
- Búsqueda de un servicio y listar los clientes de ese servicio por fecha o rango de fecha.
- Total de servicios realizados en un período de tiempo determinado, discriminado por tipo de servicio.
- Total recaudado en un período de tiempo determinado, discriminado por tipo de pago.
- Total recaudado por servicio en un período de tiempo determinado.
- Emisión de factura por servicio.
- Emisión de datos del servicio contratado.
- Emisión de reserva con las condiciones impresas.


_**Lista de Requerimientos Funcionales:**_

_**Usuarios:**_

- [x] El sistema debe permitir la carga de nuevos usuarios.
- [x] El sistema debe permitir la eliminación de usuarios.
- [x] El sistema debe permitir la modificación de los datos de usuarios.
- [x] El sistema debe permitir asignar permisos de acceso a los usuarios.
- [x] El sistema debe permitir asignar permisos de acceso a los usuarios.
- [x] El sistema debe validar los datos para el ingreso del sistema.
- [x] El sistema debe mostrar los módulos de acuerdo al permiso de acceso del usuario que ingresa.
- [x] El sistema debe permitir cerrar sesión como usuario.

_**Bar:**_

- [ ] El sistema debe permitir la carga de un nuevo proveedor.
- [ ] El sistema debe permitir la modificación de los datos de los proveedores.
- [ ] El sistema debe permitir la eliminación de un proveedor.
- [ ] El sistema debe permitir visualizar los datos del proveedor.
- [ ] El usuario podrá visualizar qué producto vende cada proveedor.
- [ ] El sistema debe permitir la carga de un nuevo producto.
- [ ] El sistema debe permitir la modificación de los datos de los productos.
- [ ] El sistema debe permitir la eliminación de un producto.
- [ ] El sistema debe permitir visualizar los datos de los productos.
- [ ] El sistema debe permitir emitir un informe mensual de bebidas de vendidas.
- [ ] El sistema debe permitir emitir un informe de existencia de stock.

_**Temática:**_

- [x] El sistema debe permitir la carga de nuevas temáticas.
- [x] El sistema debe permitir la eliminación de temáticas.
- [x] El sistema debe permitir la modificación de temáticas existentes.
- [x] El sistema debe permitir listar las distintas temáticas disponibles.

_**Servicios:**_

- [ ] El sistema debe permitir la carga de nuevos servicios.
- [ ] El sistema debe permitir la eliminación de servicios.
- [ ] El sistema debe permitir la modificación de servicios existentes.
- [ ] El sistema debe listar los servicios contratados y permitir filtrar por cliente, tipo de servicio y rango de fecha.
- [ ] El sistema debe permitir calcular y mostrar el total recaudado por tipo de servicio en un período de tiempo  determinado.

_**Clientes:**_

- [ ] El sistema debe permitir la carga de nuevos clientes.
- [ ] El sistema debe permitir la modificación de un cliente.
- [ ] El sistema debe permitir la eliminación de un cliente.
- [ ] El sistema debe mostrar un listado de clientes y permitir filtrarlos.
- [ ] El sistema debe permitir el guardado de una tarjeta de  crédito para un cliente.

_**Pagos:**_

- [ ] El sistema debe permitir seleccionar el tipo de pago en efectivo para abonar la reserva.
- [ ] El sistema debe permitir seleccionar el tipo de pago con tarjeta de crédito para abonar la reserva.
- [ ] El sistema debe permitir seleccionar el tipo de pago con tarjeta de débito para abonar la reserva.
- [ ] El sistema debe permitir la selección de la empresa de la tarjeta y el banco, del listado disponible.
- [ ] El sistema debe permitir seleccionar la cantidad de cuotas para el pago con tarjeta de crédito.
- [ ] El sistema debe permitir que el usuario habilite la cantidad de cuotas disponibles para realizar pagos.
- [ ] El sistema debe permitir que el usuario agregue una nueva empresa y banco para el pago con tarjetas.
- [ ] El sistema debe permitir que el usuario elimine una empresa y banco para el pago con tarjetas.
- [ ] El sistema debe permitir que el usuario elimine una empresa y banco para el pago con tarjetas.
- [ ] El sistema debe emitir un informe discriminado por tipo de pago y periodo de tiempo.
- [ ] El sistema debe emitir un comprobante (recibo de pago) por servicio reservado, detallando las condiciones de mismo.
- [ ] El sistema debe emitir una factura una vez realizado el pago total.

_**Combos:**_

- [ ] El sistema debe permitir la carga de nuevos combos.
- [ ] El sistema debe permitir la eliminación de combos.
- [ ] El sistema debe permitir la modificación de combos existentes.
- [ ] El sistema debe permitir listar los distintos combos disponibles.

_**Reservas:**_

- [ ] El sistema debe permitir la carga de nuevas reservas de eventos.
- [ ] El sistema debe permitir la carga de nuevas reservas de eventos.
- [ ] El sistema debe permitir la eliminación de una reserva de un  evento.
- [ ] El sistema debe permitir añadir datos adicionales a la reserva  (datos del cumpleañero, nombre, edad, etc.)
- [ ] El sistema debe permitir la selección de un combo pre-definido para la reserva.
- [ ] El sistema debe permitir la selección de una temática pre-definida para la reserva.
- [ ] El sistema debe permitir la selección opcional de uno o más servicios (video, foto, video y fotografía).
- [ ] El sistema debe permitir ingresar la cantidad de invitados niños.
- [ ] El sistema debe permitir la selección de un turno para la Reserva. (fecha y hora)
- [ ] El sistema debe mostrar un listado de reservas en espera.
- [ ] El sistema no debe permitir guardar la reserva antes de los 30 días de la fecha del evento. 
- [ ] El sistema no debe permitir guardar la reserva antes de los 30 días de la fecha del evento. 
- [ ] El sistema no debe permitir guardar la reserva con campos obligatorios incompletos.
- [ ] El sistema no debe permitir guardar la reserva de un cliente que no está dado de alta.
- [ ] El sistema debe permitir la cancelación de la reserva hasta 3 días antes como máximo.
- [ ] El sistema debe advertir que el costo al superar los 20 invitados, es de 100$ por cada invitado extra.

_**Lista de Requerimientos no Funcionales:**_

- [ ] El sistema debe proporcionar mensajes de error que sean informativos y orientados a usuario final.
- [x] El sistema debe asegurar que los datos estén protegidos del acceso no autorizado.
- [ ] El sistema debe asegurar que los datos estén protegidos del acceso no autorizado.
