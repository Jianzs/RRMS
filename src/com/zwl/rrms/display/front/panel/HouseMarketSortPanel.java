package com.zwl.rrms.display.front.panel;

import com.zwl.rrms.controller.CityController;
import com.zwl.rrms.controller.CountyController;
import com.zwl.rrms.controller.ProvinceController;
import com.zwl.rrms.display.common.ComboItem;
import com.zwl.rrms.entity.CityEntity;
import com.zwl.rrms.entity.CountyEntity;
import com.zwl.rrms.entity.ProvinceEntity;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HouseMarketSortPanel extends JPanel {
    private JComboBox provinceBox;
    private JComboBox cityBox;

    public HouseMarketSortPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createEmptyBorder(5,10,0,10));

        JLabel provinceLabel = new JLabel("省份");
        provinceLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        add(provinceLabel);
        provinceBox = new JComboBox();
        add(provinceBox);

        add(Box.createHorizontalGlue());

        JLabel cityLabel = new JLabel("城市");
        cityLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        add(cityLabel);
        cityBox = new JComboBox();
        add(cityBox);

        add(Box.createHorizontalGlue());

        JLabel sortLabel = new JLabel("排序");
        sortLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        add(sortLabel);
        JButton sortBtn = new JButton("升序");
        sortBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        add(sortBtn);

        addProvinceItem();

        provinceBox.addItemListener(e -> {
            ComboItem item = (ComboItem) e.getItem();
            addCityItem(item);
        });
    }


    private void addCityItem(ComboItem item) {
        cityBox.removeAllItems();
        if (item.getValue().equals(-1)) return ;

        java.util.List<CityEntity> citys = CityController.listByPid(item.getValue());
        citys.forEach(city -> {
            cityBox.addItem(new ComboItem(city.getContent(), city.getId()));
        });
    }

    private void addProvinceItem() {
        List<ProvinceEntity> provinces = ProvinceController.listAll();
        provinces.forEach((province) -> {
            provinceBox.addItem(new ComboItem(province.getContent(), province.getId()));
        });
        if (provinces.size() > 0)
            addCityItem(new ComboItem("null", provinces.get(0).getId()));
        else
            addCityItem(new ComboItem("null", -1));
    }
}
