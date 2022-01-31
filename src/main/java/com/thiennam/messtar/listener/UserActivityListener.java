package com.thiennam.messtar.listener;

import com.thiennam.messtar.controller.HomeController;
import com.thiennam.messtar.entity.Friendship;
import com.thiennam.messtar.entity.Room;
import com.thiennam.messtar.entity.RoomTypeEnum;
import com.thiennam.messtar.entity.User;
import com.thiennam.messtar.entity.dto.MessageDto;
import com.thiennam.messtar.entity.dto.UserDto;
import com.thiennam.messtar.service.FriendshipService;
import com.thiennam.messtar.service.RoomService;
import com.thiennam.messtar.service.UserService;
import com.thiennam.messtar.util.MessManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.List;
import java.util.Objects;

@Component
public class UserActivityListener implements ApplicationListener<SessionDisconnectEvent> {

    @Autowired
    FriendshipService friendshipService;
    @Autowired
    UserService userService;
    @Autowired
    MessManager messManager;
    @Autowired
    RoomService roomService;

    /**
     * This will catch event when user quit room
     * @param event - SessionDisconnectEvent
     */
    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        String username = Objects.requireNonNull(event.getUser()).getName();
        if (username != null) {
            User user = userService.findByUsername(username);
            user.setOnline(false);

            MessageDto messageDto = buildOfflineNotification(userService.toUserDto(user));
            // notify to all friends
            List<Friendship> friends = friendshipService.findAllFriend(user);
            for (Friendship friend : friends) {
                UserDto toFriend = userService.toUserDto(friend.getUser2());

                messageDto.setToUser(toFriend);
                messManager.sendToUser(toFriend.getUsername(), messageDto);
            }
            // clear messageDto toUser for Room
            messageDto.setToUser(null);

            // notify to all multiple room
            List<Room> rooms = roomService.findByUserAndType(user, RoomTypeEnum.MULTIPLE);
            for (Room room : rooms) {
                messageDto.setToRoomId(room.getId().toString());
                messManager.sendToRoom(room.getId(), messageDto);
            }
            userService.save(user);
        }
    }

    private MessageDto buildOfflineNotification(UserDto sender) {
        MessageDto mess = new MessageDto();
        mess.setSender(sender);
        mess.setType("NOTIFICATION");
        mess.setContent("offline");
        return mess;
    }
}
