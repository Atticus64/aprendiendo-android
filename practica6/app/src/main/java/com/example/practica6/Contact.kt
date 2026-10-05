package com.example.practica6

data class Contacto (
    val id: Int,
    val nombre: String,
    val telefono: String
)


fun obtenerContactosDummy(): List<Contacto> {

    return listOf(
        Contacto(1, "Ana García", "555-7832"),
        Contacto(2, "Pedro Romero", "555-9823"),
        Contacto(3, "Marco Diaz", "555-1243"),
        Contacto(4, "María López", "555-8732"),
        Contacto(5, "Carlos Ramírez", "555-9384"),
        Contacto(6, "Sofía Torres", "555-9837"),
        Contacto(7, "Jorge Medina", "555-9392"),
        Contacto(8, "Laura Salazar", "555-8495"),
        Contacto(9, "Pedro Castillo", "555-0932"),
        Contacto(10, "Elena Rojas", "555-7845"),
        Contacto(11, "Diego Castro", "555-4536"),
        Contacto(12, "Valeria Silva", "555-6732"),
        Contacto(13, "Ricardo Soto", "555-8722"),
    )
}