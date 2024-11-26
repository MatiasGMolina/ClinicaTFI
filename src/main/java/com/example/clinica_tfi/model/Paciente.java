package com.example.clinica_tfi.model;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@Entity
@NoArgsConstructor
public class Paciente{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String dni;
        private String nombre;
        private String apellido;
        private String email;
        private String telefono;

        @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
        @JoinColumn(name = "historia_clinica_id")
        private HistoriaClinica historiaClinica;


        public void agregarHistoriaClinica(HistoriaClinica historiaClinica) {
                this.historiaClinica = historiaClinica;
        }


        public void agregarDiagnostico(Diagnostico diagnostico) {
                if (this.historiaClinica == null) {
                        throw new RuntimeException("El paciente no tiene una historia clínica asociada.");
                }
                this.historiaClinica.agregarDiagnostico(diagnostico);
        }


        public void agregarEvolucion(String nombreDiagnostico, Evolucion evolucion) {
                if (this.historiaClinica == null) {
                        throw new RuntimeException("El paciente no tiene una historia clínica asociada.");
                }
                Diagnostico diagnostico = this.historiaClinica.getDiagnosticoPorNombre(nombreDiagnostico);
                diagnostico.agregarEvolucion(evolucion);
        }

}
