package com.fling.fllingbe.domain.bouquet.application;


import com.fling.fllingbe.domain.bouquet.domain.Bouquet;
import com.fling.fllingbe.domain.bouquet.dto.*;
import com.fling.fllingbe.domain.bouquet.repository.BouquetRepository;
import com.fling.fllingbe.domain.flower.domain.Flower;
import com.fling.fllingbe.domain.flower.dto.FlowerInfo;
import com.fling.fllingbe.domain.flower.repository.FlowerRepository;
import com.fling.fllingbe.domain.item.application.DecoItemService;
import com.fling.fllingbe.domain.item.domain.DecoItem;
import com.fling.fllingbe.domain.item.domain.DecoType;
import com.fling.fllingbe.domain.item.domain.RibbonType;
import com.fling.fllingbe.domain.item.domain.WrapperType;
import com.fling.fllingbe.domain.item.repository.DecoItemRepository;
import com.fling.fllingbe.domain.item.repository.DecoTypeRepository;
import com.fling.fllingbe.domain.item.repository.RibbonRepository;
import com.fling.fllingbe.domain.item.repository.WrapperTypeRepository;
import com.fling.fllingbe.domain.user.domain.User;
import com.fling.fllingbe.domain.user.exception.UserNotFoundException;
import com.fling.fllingbe.domain.user.repository.UserRepository;
import com.fling.fllingbe.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BouquetService {
    private final  BouquetRepository bouquetRepository;
    private final UserRepository userRepository;
    private final FlowerRepository flowerRepository;
    private final RibbonRepository ribbonRepository;
    private final WrapperTypeRepository wrapperTypeRepository;
    private final DecoTypeRepository decoTypeRepository;
    private final DecoItemRepository decoItemRepository;
    private final DecoItemService decoItemService;
    public Bouquet createNewBouquet(User user) {
        List<Bouquet> receiverBouquets = bouquetRepository.findAllByUser(user);
        Bouquet newBouquet = new Bouquet().builder()
                .user(user)
                .decoItem1(receiverBouquets.get(0).getDecoItem1())
                .decoItem2(receiverBouquets.get(0).getDecoItem2())
                .decoItem3(receiverBouquets.get(0).getDecoItem3())
                .ribbonType(receiverBouquets.get(0).getRibbonType())
                .wrapperType(receiverBouquets.get(0).getWrapperType())
                .build();
        bouquetRepository.save(newBouquet);
        return newBouquet;
    }

    public String createFirstBouquet(Authentication authentication , CreateBouquetRequest request) {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(()-> new UserNotFoundException());
        Bouquet newBouquet = new Bouquet().builder()
                .user(user)
                .decoItem1(decoTypeRepository.findByDecoTypeName("undefined").get())
                .decoItem2(decoTypeRepository.findByDecoTypeName("undefined").get())
                .decoItem3(decoTypeRepository.findByDecoTypeName("undefined").get())
                .ribbonType(ribbonRepository.findByRibbonName(request.getRibbon()).get())
                .wrapperType(wrapperTypeRepository.findByWrapperName(request.getWrapper()).get())
                .build();
        bouquetRepository.save(newBouquet);
        return "꽃다발 생성에 성공하였습니다.";
    }
    public GetBouquetResponse getBouquetResponse(Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(()-> new UserNotFoundException());
        List<Bouquet> bouquets = bouquetRepository.findAllByUser(user);
        if (bouquets.size() != 0) {
            List<BouquetInfo> bouquetInfos = new ArrayList<>();
            BouquetDesign bouquetDesign = getBouquetDesign(bouquets.get(0));
            for (Bouquet bouquet : bouquets) {
                List<Flower> flowers = flowerRepository.findAllByBouquetId(bouquet);
                List<FlowerInfo> flowerInfoList = flowers.stream()
                        .map(FlowerInfo::fromEntity)
                        .toList();
                BouquetInfo bouquetInfo = new BouquetInfo(bouquet.getBouquetId(), flowerInfoList);
                bouquetInfos.add(bouquetInfo);
            }
            GetBouquetResponse getBouquetResponse = new GetBouquetResponse(bouquetDesign, bouquetInfos);
            return getBouquetResponse;
        } else {
            GetBouquetResponse getBouquetResponse = new GetBouquetResponse(null,null);
            return getBouquetResponse;
        }
    }
    public BouquetDesign getBouquetDesign(Bouquet bouquet) {
        BouquetDesign bouquetDesign = new BouquetDesign(bouquet.getWrapperType().getWrapperName()
                ,bouquet.getRibbonType().getRibbonName()
                ,bouquet.getDecoItem1().getDecoTypeName()
                ,bouquet.getDecoItem2().getDecoTypeName()
                ,bouquet.getDecoItem3().getDecoTypeName());
        return bouquetDesign;
    }
    public String updateBouquet(UpdateBouquetRequest request, Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(()-> new UserNotFoundException());
        List<Bouquet> bouquets = bouquetRepository.findAllByUser(user);
        List<DecoItem> predecoItemList = decoItemRepository.findAllByUserAndIsUsing(user,true);
        decoItemService.toFalse(user,predecoItemList);
        List<DecoItem> decoItems = decoItemService.toTrue(user,request);
        for (Bouquet bouquet : bouquets) {
            Bouquet newbouquet = new Bouquet().builder()
                    .bouquetId(bouquet.getBouquetId())
                    .wrapperType(wrapperTypeRepository.findByWrapperName(request.getWrapper()).get())
                    .ribbonType(ribbonRepository.findByRibbonName((request.getRibbon())).get())
                    .decoItem1(decoItems.get(0).getDecoType())
                    .decoItem2(decoItems.get(1).getDecoType())
                    .decoItem3(decoItems.get(2).getDecoType())
                    .user(user)
                    .build();
            bouquetRepository.save(newbouquet);
        }
        return "꽃다발 수정에 성공하였습니다.";
    }
}
