package com.fling.fllingbe.global.dataSetting;

import com.fling.fllingbe.domain.item.domain.DecoItem;
import com.fling.fllingbe.domain.item.repository.DecoItemRepository;
import com.fling.fllingbe.domain.user.domain.User;
import com.fling.fllingbe.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class RandomDecoSetter{
    private final RedisTemplate<String, String> redisTemplate;
    private final UserRepository userRepository;
    private final DecoItemRepository decoItemRepository;

    public static final Long ExpiratoinTime = 1108000000L;
    Random random = new Random();
    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void setAllUser(){
        List<User> userList= userRepository.findAllBy();
        for (User user : userList) {
            this.setOneUser(user);
        }
    }
    public void setOneUser(User user){
        List<DecoItem> decoItemList = decoItemRepository.findAllByUserAndDecoType_PriceGreaterThan(user, 0);
        List<Integer> randomList = new ArrayList<>();
        for(int number = 0; number < 3 ; number++){
            int listSize = decoItemList.size();
            int randomNumber = 0;
            while(true){
                randomNumber = random.nextInt(listSize);
                if (!randomList.contains(randomNumber)){
                    randomList.add(randomNumber);
                    break;
                }
            }
            DecoItem decoItem = decoItemList.get(randomNumber);
            redisTemplate.opsForValue().set(
                    user.getEmail()+"_"+number,
                    String.valueOf(decoItem.getDecoType().getDecoTypeId()),
                    ExpiratoinTime,
                    TimeUnit.MILLISECONDS
            );
            log.info("Set random deco email:" + user.getEmail()+" number:"+number+" item:"+decoItem.getDecoType().getDecoTypeName());
        }
    }
}
