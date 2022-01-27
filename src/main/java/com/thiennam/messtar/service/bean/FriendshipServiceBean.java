package com.thiennam.messtar.service.bean;

import com.thiennam.messtar.entity.Friendship;
import com.thiennam.messtar.entity.FriendshipTypeEnum;
import com.thiennam.messtar.entity.User;
import com.thiennam.messtar.repository.FriendshipRepository;
import com.thiennam.messtar.service.FriendshipService;
import com.thiennam.messtar.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service(value = FriendshipService.NAME)
public class FriendshipServiceBean implements FriendshipService {

    @Autowired
    FriendshipRepository friendshipRepository;
    @Autowired
    RoomService roomService;

    @Override
    public void addFriend(User user1, User user2) {
        Friendship friendship1 = findFriendship(user1, user2);
        if (friendship1 == null) {
            LocalDateTime now = LocalDateTime.now();
            friendship1 = new Friendship();
            friendship1.setUser1(user1);
            friendship1.setUser2(user2);
            friendship1.setType(FriendshipTypeEnum.FRIEND);
            friendship1.setCreatedTime(now);

            Friendship friendship2 = new Friendship();
            friendship2.setUser1(user2);
            friendship2.setUser2(user1);
            friendship2.setType(FriendshipTypeEnum.FRIEND);
            friendship2.setCreatedTime(now);

            friendshipRepository.saveAll(Arrays.asList(friendship1, friendship2));
        }
        roomService.addNewSingleRoom(user1, user2);
    }

    @Override
    public Friendship findFriendship(User user1, User user2) {
        List<Friendship> friendships = friendshipRepository.findByUser1(user1);
        for (Friendship friendship : friendships) {
            if (friendship.getUser2().equals(user2)) {
                return friendship;
            }
        }
        return null;
    }

    @Override
    public List<Friendship> findAllFriend(User user1) {
        return friendshipRepository.findByUser1(user1);
    }
}
