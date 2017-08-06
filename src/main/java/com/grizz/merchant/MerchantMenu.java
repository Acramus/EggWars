package com.grizz.merchant;

import com.grizz.menu.Menu;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gbtank.
 */
public class MerchantMenu extends Menu {

    @Getter private Map<Integer, MerchantTrade> tradeMap = new HashMap<>();

}
