package alessandra_alessandro.ketchapp_bff.controllers;

import alessandra_alessandro.ketchapp_bff.models.apicall.AppointmentApiCall;
import alessandra_alessandro.ketchapp_bff.models.responses.AppointmentResponse;
import alessandra_alessandro.ketchapp_bff.utils.ApiCall;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class AppointmentsControllers {
    public AppointmentApiCall convertResponseToApiCall(AppointmentResponse response) {
        if (response == null) {
            return null;
        }
        return new AppointmentApiCall(
                response.getId(),
                response.getUserUUID(),
                response.getName(),
                response.getStartAt(),
                response.getEndAt(),
                response.getCreatedAt()
        );
    }

    public AppointmentResponse convertApiCallToResponse(AppointmentApiCall apiCall) {
        if (apiCall == null) {
            return null;
        }
        return new AppointmentResponse(
                apiCall.getId(),
                apiCall.getUserUUID(),
                apiCall.getName(),
                apiCall.getStartAt(),
                apiCall.getEndAt(),
                apiCall.getCreatedAt()
        );
    }

    public List<AppointmentResponse> getAppointments() {
        String url = "/appointments";
        AppointmentApiCall[] appointments = ApiCall.get(url, new TypeReference<AppointmentApiCall[]>() {
        });
        return Stream.of(Objects.requireNonNull(appointments))
                .filter(Objects::nonNull)
                .map(this::convertApiCallToResponse)
                .toList();
    }
    
    public AppointmentResponse getAppointment(UUID uuid) {
        String url = "/appointments/" + uuid;
        AppointmentApiCall appointment = ApiCall.get(url, new TypeReference<AppointmentApiCall>() {
        });
        return convertApiCallToResponse(appointment);
    }
    
    public AppointmentResponse createAppointment(AppointmentResponse appointmentResponse) {
        String url = "/appointments";
        AppointmentApiCall createdAppointment = ApiCall.post(url, convertResponseToApiCall(appointmentResponse), AppointmentApiCall.class);
        return convertApiCallToResponse(createdAppointment);
    }
    
    public AppointmentResponse deleteAppointment(UUID uuid) {
        String url = "/appointments/" + uuid;
        AppointmentApiCall deletedAppointment = ApiCall.delete(url, AppointmentApiCall.class);
        return convertApiCallToResponse(deletedAppointment);
    }
}
