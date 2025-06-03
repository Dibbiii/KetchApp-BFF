package alessandra_alessandro.ketchapp_bff.controllers;

import alessandra_alessandro.ketchapp_bff.models.apicall.FriendApiCall;
import alessandra_alessandro.ketchapp_bff.models.responses.FriendResponse;
import alessandra_alessandro.ketchapp_bff.utils.ApiCall;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FriendsControllers {
    public FriendResponse convertApiCallToResponse(FriendApiCall friendApiCall) {
        if (friendApiCall == null) {
            return null;
        }
        return new FriendResponse(
                friendApiCall.getId(),
                friendApiCall.getUserUUID(),
                friendApiCall.getFriendUUID(),
                friendApiCall.getCreatedAt()
        );
    }

    public FriendApiCall convertResponseToApiCall(FriendResponse friendResponse) {
        if (friendResponse == null) {
            return null;
        }
        return new FriendApiCall(
                friendResponse.getId(),
                friendResponse.getUserUUID(),
                friendResponse.getFriendUUID(),
                friendResponse.getCreatedAt()
        );
    }

    public List<FriendResponse> getFriends() {
        String url = "/friends";
        FriendApiCall[] response = ApiCall.get(url, new TypeReference<FriendApiCall[]>() {});
        return Stream.of(Objects.requireNonNull(response))
                .map(this::convertApiCallToResponse)
                .toList();
    }
    
    public FriendResponse getFriend(UUID uuid) {
        String url = "/friends/" + uuid;
        FriendApiCall response = ApiCall.get(url, new TypeReference<FriendApiCall>() {});
        return convertApiCallToResponse(response);
    }
    
    public FriendResponse createFriend(FriendResponse friendResponse) {
        String url = "/friends";
        FriendApiCall response = ApiCall.post(url, convertResponseToApiCall(friendResponse), FriendApiCall.class);
        return convertApiCallToResponse(response);
    }
    
    public FriendResponse deleteFriend(UUID uuid) {
        String url = "/friends/" + uuid;
        FriendApiCall response = ApiCall.delete(url, FriendApiCall.class);
        return convertApiCallToResponse(response);
    }
}