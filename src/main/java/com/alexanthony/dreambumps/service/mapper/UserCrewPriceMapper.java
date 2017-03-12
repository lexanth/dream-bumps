package com.alexanthony.dreambumps.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.alexanthony.dreambumps.domain.UserCrewPrice;
import com.alexanthony.dreambumps.service.dto.UserCrewPriceDTO;

@Mapper(componentModel = "spring", uses = {UserMapper.class, })
public interface UserCrewPriceMapper {
  
  @Mapping(source = "user.id", target = "userId")
  UserCrewPriceDTO userCrewPriceToUserCrewPriceDTO(UserCrewPrice userCrewPrice);

  List<UserCrewPriceDTO> userCrewPricesToUserCrewPriceDTOs(List<UserCrewPrice> userCrewPrices);

  @Mapping(source = "userId", target = "user")
  UserCrewPrice userCrewPriceDTOToUserCrewPrice(UserCrewPriceDTO userCrewPriceDTO);

  List<UserCrewPrice> userCrewPriceDTOsToUserCrewPrices(List<UserCrewPriceDTO> userCrewPriceDTOs);

}
