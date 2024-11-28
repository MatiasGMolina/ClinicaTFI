package com.example.clinica_tfi.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class Paciente{
        private Long id;
        private String dni;
        private String nombre;
        private String apellido;
        private String email;
        private String telefono;
        private HistoriaClinica historiaClinica;

        public void validarHistoriaClinica() {
                if (this.historiaClinica == null) {
                        throw new RuntimeException("El paciente no tiene una historia clínica asociada.");
                }
        }

        public void agregarHistoriaClinica(HistoriaClinica historiaClinica) {
                this.historiaClinica = historiaClinica;
        }

        public void agregarDiagnostico(Diagnostico diagnostico) {
                if (this.historiaClinica == null) {
                        throw new RuntimeException("El paciente no tiene una historia clínica asociada.");
                }
                this.historiaClinica.agregarDiagnostico(diagnostico);
        }

        public void agregarEvolucion(Long idDiagnostico, String informe, Medico medico) {

                // Delegar en HistoriaClinica la lógica de agregar evolución
                this.historiaClinica.agregarEvolucion(idDiagnostico, informe, medico);
        }

        public void agregarRecetaDigital(Long idEvolucion, Long idDiagnostico, List<Medicamento> medicamentos, String observaciones) {
                if (this.historiaClinica == null) {
                        throw new RuntimeException("El paciente no tiene una historia clínica asociada.");
                }
                // Delegar a HistoriaClinica
                this.historiaClinica.agregarRecetaDigital(idEvolucion, idDiagnostico, medicamentos, observaciones);
        }

        public void agregarPedidoLaboratorio(Long idEvolucion, Long idDiagnostico, String nombreEstudio, String observaciones) {
                if (this.historiaClinica == null) {
                        throw new RuntimeException("El paciente no tiene una historia clínica asociada.");
                }
                // Delegar a HistoriaClinica
                this.historiaClinica.agregarPedidoLaboratorio(idEvolucion, idDiagnostico, nombreEstudio, observaciones);
        }
}