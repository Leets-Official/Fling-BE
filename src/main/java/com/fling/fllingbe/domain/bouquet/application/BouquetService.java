package com.fling.fllingbe.domain.bouquet.application;


import com.fling.fllingbe.domain.bouquet.domain.Bouquet;
import com.fling.fllingbe.domain.bouquet.dto.BouquetDesign;
import com.fling.fllingbe.domain.bouquet.dto.BouquetInfo;
import com.fling.fllingbe.domain.bouquet.dto.CreateBouquetRequest;
import com.fling.fllingbe.domain.bouquet.dto.GetBouquetResponse;
import com.fling.fllingbe.domain.bouquet.repository.BouquetRepository;
import com.fling.fllingbe.domain.flower.domain.Flower;
import com.fling.fllingbe.domain.flower.dto.FlowerInfo;
import com.fling.fllingbe.domain.flower.repository.FlowerRepository;
import com.fling.fllingbe.domain.user.domain.User;
import com.fling.fllingbe.domain.user.exception.UserNotFoundException;
import com.fling.fllingbe.domain.user.repository.UserRepository;
import com.fling.fllingbe.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BouquetService {
    private final  BouquetRepository bouquetRepository;
    private final UserRepository userRepository;
    private final FlowerRepository flowerRepository;
    private final JwtProvider jwtProvider;
    public Bouquet createNewBouquet(User user) {
        Bouquet receiverBouquet = bouquetRepository.findByUser(user).get();
        Bouquet newBouquet = new Bouquet().builder()
                .user(user)
                .ribbon(receiverBouquet.getRibbon())
                .wrapper(receiverBouquet.getWrapper())
                .build();
        bouquetRepository.save(newBouquet);
        return newBouquet;
    }

    public String createFirstBouquet(UUID id , CreateBouquetRequest request) {
        User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException());
        Bouquet newBouquet = new Bouquet().builder()
                .user(user)
                .ribbon(request.getRibbon())
                .wrapper(request.getWrapper())
                .build();
        bouquetRepository.save(newBouquet);
        return "꽃다발 생성에 성공하였습니다.";
    }
    public GetBouquetResponse getBouquetResponse(String token) {
        User user = userRepository.findByEmail(jwtProvider.getEmail(token)).orElseThrow(()-> new UserNotFoundException());
        List<Bouquet> bouquets = bouquetRepository.findAllByUser(user);
        List<BouquetInfo> bouquetInfos = new ArrayList<>();
        BouquetDesign bouquetDesign = new BouquetDesign(bouquets.get(0).getWrapper()
                ,bouquets.get(0).getRibbon()
                ,bouquets.get(0).getDecoItem1().getDecoTypeName()
                ,bouquets.get(0).getDecoItem2().getDecoTypeName()
                ,bouquets.get(0).getDecoItem3().getDecoTypeName());
        for(Bouquet bouquet : bouquets){
            List<Flower> flowers = flowerRepository.findAllByBouquetId(bouquet);
            List<FlowerInfo> flowerInfoList = flowers.stream()
                    .map(FlowerInfo::fromEntity)
                    .toList();
            BouquetInfo bouquetInfo = new BouquetInfo(bouquet.getBouquetId(),flowerInfoList);
            bouquetInfos.add(bouquetInfo);
            bouquetDesign = new BouquetDesign(bouquet.getWrapper()
                    ,bouquet.getRibbon()
                    ,bouquet.getDecoItem1().getDecoTypeName()
                    ,bouquet.getDecoItem2().getDecoTypeName()
                    ,bouquet.getDecoItem3().getDecoTypeName());
        }
        GetBouquetResponse getBouquetResponse = new GetBouquetResponse(bouquetDesign,bouquetInfos);
        return getBouquetResponse;
    }
}
