package com.exercises.ejercicios_api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "patient")
@Data
public class PatientModel {

    // 1. CLAVE PRIMARIA (PK)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Long patientId;

    // 2. CLAVE FORÁNEA (FK) a la tabla 'users'
    @Column(name = "user_id", unique = true, nullable = false)
    private Long userId;

    // IDENTIFIER (Documento Identidad)
    @Column(name = "identifier_system")
    private String identifierSystem;

    @Column(name = "identifier_value", unique = true, nullable = false)
    private String identifierValue; // 'documento_identidad' en tu modelo simple

    @Column(name = "identifier_type")
    private String identifierType;  // 'tipo_documento' en tu modelo simple

    // HUMAN NAME
    @Column(name = "given_name", nullable = false)
    private String givenName;       // 'nombre' en tu modelo simple

    @Column(name = "family_name", nullable = false)
    private String familyName;      // 'apellidos' en tu modelo simple

    @Column(name = "full_name")
    private String fullName;        // Nombre completo calculado

    // DEMOGRAPHICS
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;    // 'fecha_nacimiento' en tu modelo simple

    @Column(name = "gender")
    private String gender;

    // CONTACT POINT
    @Column(name = "telecom_phone")
    private String telecomPhone;    // Teléfono

    @Column(name = "telecom_email")
    private String telecomEmail;    // Email (se puede obtener del users.email)

    // ADDRESS
    @Column(name = "address_line")
    private String addressLine;     // 'direccion' en tu modelo simple

    @Column(name = "address_city")
    private String addressCity;     // 'ciudad' en tu modelo simple

    @Column(name = "address_state")
    private String addressState;

    @Column(name = "address_postal_code")
    private String addressPostalCode;

    @Column(name = "address_country")
    private String addressCountry;  // 'pais' en tu modelo simple

    // CONTACTO DE EMERGENCIA
    @Column(name = "contact_relationship")
    private String contactRelationship; // Nueva columna

    @Column(name = "contact_name")
    private String contactName;     // 'contacto_emergencia_nombre'

    @Column(name = "contact_phone")
    private String contactPhone;    // 'contacto_emergencia_telefono'

    // CLINICAL
    @Column(name = "blood_type")
    private String bloodType;       // 'grupo_sanguineo' en tu modelo simple

    // Mapeamos JSONB a String. Se usará Serialización/Deserialización
    @Column(name = "allergies", columnDefinition = "jsonb")
    private String allergies;       // Historial de alergias

    @Column(name = "preferred_language")
    private String preferredLanguage;

    // EHR INTEGRATION
    @Column(name = "external_id")
    private String externalId;      // 'id_ehr_externo' en tu modelo simple

    @Column(name = "external_system")
    private String externalSystem;

    // METADATA
    @Column(name = "active")
    private Boolean active;

    @Column(name = "photo_url")
    private String photoUrl;        // 'foto_url' en tu modelo simple

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "synced_with_ehr")
    private Boolean syncedWithEhr;

    @Column(name = "last_sync_date")
    private LocalDateTime lastSyncDate;
}
