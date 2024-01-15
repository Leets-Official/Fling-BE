package com.fling.fllingbe.domain.flower.application;


import com.fling.fllingbe.domain.bouquet.application.BouquetService;
import com.fling.fllingbe.domain.bouquet.domain.Bouquet;
import com.fling.fllingbe.domain.bouquet.repository.BouquetRepository;
import com.fling.fllingbe.domain.flower.domain.Flower;
import com.fling.fllingbe.domain.flower.dto.ReceivedFlower;
import com.fling.fllingbe.domain.flower.dto.SentFlower;
import com.fling.fllingbe.domain.flower.repository.FlowerRepository;
import com.fling.fllingbe.domain.flower.dto.FlowerRequest;
import com.fling.fllingbe.domain.item.application.CardItemService;
import com.fling.fllingbe.domain.item.application.FlowerItemService;
import com.fling.fllingbe.domain.item.domain.CardType;
import com.fling.fllingbe.domain.item.domain.FlowerType;
import com.fling.fllingbe.domain.user.domain.User;
import com.fling.fllingbe.domain.user.exception.UserNotFoundException;
import com.fling.fllingbe.domain.user.repository.UserRepository;
import com.fling.fllingbe.global.error.exception.ServiceException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FlowerService {
    private final FlowerRepository flowerRepository;
    private final UserRepository userRepository;
    private final CardItemService cardItemService;
    private final BouquetRepository bouquetRepository;
    private final FlowerItemService flowerItemService;
    private final BouquetService bouquetService;

    @Transactional
    public String writeLetter(FlowerRequest request, String senderEmail, UUID ReceiverId) {
        try {
            User sender = userRepository.findByEmail(senderEmail).orElseThrow(()->new UserNotFoundException());
            User receiver = userRepository.findByUserId(ReceiverId).orElseThrow(()->new UserNotFoundException());
            FlowerType flowerType = flowerItemService.minusFlowerItem(request, sender);
            CardType cardType = cardItemService.minusCardItem(request, sender);
            List<Flower> flowers = flowerRepository.findAllByReceiver(receiver);
            System.out.println(flowers.size());
            Bouquet bouquet;

            if (flowers.size() % 5 == 0 && flowers.size() != 0) {
                bouquet = bouquetService.createNewBouquet(receiver);
            } else {
                List<Bouquet> bouquets = bouquetRepository.findAllByUser(receiver);
                bouquet = bouquets.get(bouquets.size() - 1);
            }

            Flower flower = new Flower().builder()
                    .letter(request.getLetter())
                    .flowerType(flowerType)
                    .cardType(cardType)
                    .sender(sender)
                    .receiver(receiver)
                    .bouquetId(bouquet)
                    .build();
            flowerRepository.save(flower);

            return "저장에 성공하였습니다.";
        } catch (ServiceException e) {
            throw e;
        }
    }

    public List<SentFlower> getSendedFlower(String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(()->new UserNotFoundException());
        List<Flower> flowerList = flowerRepository.findAllBySender(user);
        List<SentFlower> sentFlowerList = flowerList.stream().map(SentFlower::fromEntity).toList();
        return sentFlowerList;
    }

    public List<ReceivedFlower> getReceivedFlower(String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(()->new UserNotFoundException());
        List<Flower> flowerList = flowerRepository.findAllByReceiver(user);
        List<ReceivedFlower> receivedFlowerList = flowerList.stream().map(ReceivedFlower::fromEntity).toList();
        return receivedFlowerList;
    }
}

