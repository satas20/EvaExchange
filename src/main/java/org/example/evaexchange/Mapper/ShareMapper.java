package org.example.evaexchange.Mapper;

import org.example.evaexchange.Dto.ShareDto;
import org.example.evaexchange.Entity.Share;
import org.springframework.stereotype.Component;

@Component
public class ShareMapper {

    public ShareDto toDto(Share share) {
        ShareDto dto = new ShareDto();
        dto.setId(share.getId());
        dto.setSymbol(share.getSymbol());
        dto.setPrice(share.getPrice());
        return dto;
    }

    public Share toEntity(ShareDto dto) {
        Share share = new Share();
        share.setId(dto.getId());
        share.setSymbol(dto.getSymbol());
        share.setPrice(dto.getPrice());
        return share;
    }
}