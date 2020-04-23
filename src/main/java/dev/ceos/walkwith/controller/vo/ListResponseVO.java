package dev.ceos.caloringmvp.controller.vo;

import lombok.Data;
import java.util.List;

@Data
public class ListResponseVO {
    public ListResponseVO(){ }

    public ListResponseVO(List response){
        this.response = response;
    }

    private List response;
}
