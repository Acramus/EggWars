package com.grizz.merchant;

import com.grizz.menu.Menu;
import com.grizz.nms.api.MerchantTrade;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gbtank.
 */
public class MerchantMenu extends Menu {

    @Getter private List<MerchantTrade> trades = new ArrayList<>();

}
