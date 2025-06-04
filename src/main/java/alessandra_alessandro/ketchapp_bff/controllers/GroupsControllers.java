package alessandra_alessandro.ketchapp_bff.controllers;

import alessandra_alessandro.ketchapp_bff.models.apicall.GroupApiCall;
import alessandra_alessandro.ketchapp_bff.models.responses.GroupResponse;
import alessandra_alessandro.ketchapp_bff.utils.ApiCall;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class GroupsControllers {
    public GroupApiCall convertResponseToApiCall(GroupResponse groupResponse) {
        if (groupResponse == null) {
            return null;
        }
        return new GroupApiCall(
                groupResponse.getId(),
                groupResponse.getCreatedAt()
        );
    }

    public GroupResponse convertApiCallToResponse(GroupApiCall groupApiCall) {
        if (groupApiCall == null) {
            return null;
        }
        return new GroupResponse(
                groupApiCall.getId(),
                groupApiCall.getCreatedAt()
        );
    }

    public List<GroupResponse> getGroups() {
        String url = "/groups";
        GroupApiCall[] response = ApiCall.get(url, new TypeReference<GroupApiCall[]>() {
        });
        return Stream.of(Objects.requireNonNull(response))
                .map(this::convertApiCallToResponse)
                .toList();
    }

    public GroupResponse getGroup(UUID uuid) {
        String url = "/groups/" + uuid;
        GroupApiCall response = ApiCall.get(url, new TypeReference<GroupApiCall>() {
        });
        return convertApiCallToResponse(response);
    }

    public GroupResponse createGroup(GroupResponse groupResponse) {
        String url = "/groups";
        GroupApiCall response = ApiCall.post(url, convertResponseToApiCall(groupResponse), GroupApiCall.class);
        return convertApiCallToResponse(response);
    }

    public GroupResponse deleteGroup(UUID uuid) {
        String url = "/groups/" + uuid;
        GroupApiCall response = ApiCall.delete(url, GroupApiCall.class);
        return convertApiCallToResponse(response);
    }

}
