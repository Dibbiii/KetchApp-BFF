package alessandra_alessandro.ketchapp_bff.routes;

import alessandra_alessandro.ketchapp_bff.controllers.AppointmentsControllers;
import alessandra_alessandro.ketchapp_bff.models.responses.AppointmentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentsRoutes {
    @Autowired
    AppointmentsControllers appointmentsControllers;
    
    @Operation(summary = "Get all appointments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointments retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<AppointmentResponse>> getAppointments() {
        List<AppointmentResponse> appointments = appointmentsControllers.getAppointments();
        return ResponseEntity.ok(appointments);
    }
    
    @Operation(summary = "Get all appointments by user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointments retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{uuid}")
    public ResponseEntity<AppointmentResponse> getAppointment(@PathVariable UUID uuid) {
        AppointmentResponse appointment = appointmentsControllers.getAppointment(uuid);
        if (appointment != null) {
            return ResponseEntity.ok(appointment);
        } else {
            return ResponseEntity.status(500).build();
        }
    }
    
    @Operation(summary = "Create a new appointment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Appointment created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<AppointmentResponse> createAppointment(@RequestBody AppointmentResponse appointmentResponse) {
        AppointmentResponse createdAppointment = appointmentsControllers.createAppointment(appointmentResponse);
        return ResponseEntity.status(201).body(createdAppointment);
    }
    
    @Operation(summary = "Delete an appointment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Appointment deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Appointment not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{uuid}")
    public ResponseEntity<AppointmentResponse> deleteAppointment(@PathVariable UUID uuid) {
        AppointmentResponse deletedAppointment = appointmentsControllers.deleteAppointment(uuid);
        if (deletedAppointment == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok(deletedAppointment);
    }
}
