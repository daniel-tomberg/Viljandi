package com.viljandi.config;

import com.viljandi.entity.*;
import com.viljandi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private UpcomingDeliveryRepository deliveryRepository;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OrderLineRepository orderLineRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        Client client = new Client();
        client.setClientId("8290");
        client.setStoreName("BAUHAUS Central Warehouse (8290)");
        client.setUsername("bauhaus");
        client.setPassword(passwordEncoder.encode("password"));
        clientRepository.save(client);

        createSampleDeliveries(client.getClientId());

        createSampleOrders(client.getClientId());
    }

    private void createSampleDeliveries(String clientId) {
        UpcomingDelivery delivery1 = new UpcomingDelivery();
        delivery1.setCategoryName("Frames");
        delivery1.setDeliveryWeek(37);
        delivery1.setClientId(clientId);
        deliveryRepository.save(delivery1);

        UpcomingDelivery delivery2 = new UpcomingDelivery();
        delivery2.setCategoryName("External Doors");
        delivery2.setDeliveryWeek(36);
        delivery2.setClientId(clientId);
        deliveryRepository.save(delivery2);

        UpcomingDelivery delivery3 = new UpcomingDelivery();
        delivery3.setCategoryName("Internal Doors");
        delivery3.setDeliveryWeek(38);
        delivery3.setClientId(clientId);
        deliveryRepository.save(delivery3);

        UpcomingDelivery delivery4 = new UpcomingDelivery();
        delivery4.setCategoryName("Window Factory");
        delivery4.setDeliveryWeek(36);
        delivery4.setClientId(clientId);
        deliveryRepository.save(delivery4);

        UpcomingDelivery delivery5 = new UpcomingDelivery();
        delivery5.setCategoryName("Project Doors");
        delivery5.setAdditionalDescription("Painted");
        delivery5.setDeliveryWeek(40);
        delivery5.setClientId(clientId);
        deliveryRepository.save(delivery5);

        UpcomingDelivery delivery6 = new UpcomingDelivery();
        delivery6.setCategoryName("Project Doors");
        delivery6.setAdditionalDescription("Laminated / Veneered");
        delivery6.setDeliveryWeek(42);
        delivery6.setClientId(clientId);
        deliveryRepository.save(delivery6);

        UpcomingDelivery delivery7 = new UpcomingDelivery();
        delivery7.setCategoryName("Akacija");
        delivery7.setAdditionalDescription("Forte and Pine Doors");
        delivery7.setDeliveryWeek(39);
        delivery7.setClientId(clientId);
        deliveryRepository.save(delivery7);
    }

    private void createSampleOrders(String clientId) {

        Order order1 = new Order();
        order1.setPoNumber("4501143161");
        order1.setOrderDate(LocalDate.of(2025, 7, 8));
        order1.setExpectedDelivery(LocalDate.of(2025, 10, 31));
        order1.setStatus(Order.OrderStatus.PROCESSING);
        order1.setClientId(clientId);
        orderRepository.save(order1);

        Order order2 = new Order();
        order2.setPoNumber("4501143162");
        order2.setOrderDate(LocalDate.of(2025, 7, 7));
        order2.setExpectedDelivery(LocalDate.of(2025, 10, 31));
        order2.setStatus(Order.OrderStatus.PROCESSING);
        order2.setClientId(clientId);
        orderRepository.save(order2);

        Order order3 = new Order();
        order3.setPoNumber("4501105041");
        order3.setOrderDate(LocalDate.of(2025, 6, 10));
        order3.setExpectedDelivery(LocalDate.of(2025, 7, 15));
        order3.setStatus(Order.OrderStatus.DELIVERED);
        order3.setClientId(clientId);
        orderRepository.save(order3);

        Order order4 = new Order();
        order4.setPoNumber("4501105045");
        order4.setOrderDate(LocalDate.of(2025, 6, 9));
        order4.setExpectedDelivery(LocalDate.of(2025, 7, 15));
        order4.setStatus(Order.OrderStatus.DELIVERED);
        order4.setClientId(clientId);
        orderRepository.save(order4);

        Order order5 = new Order();
        order5.setPoNumber("4501105058");
        order5.setOrderDate(LocalDate.of(2025, 6, 9));
        order5.setExpectedDelivery(LocalDate.of(2025, 7, 15));
        order5.setStatus(Order.OrderStatus.DELIVERED);
        order5.setClientId(clientId);
        orderRepository.save(order5);

        createOrderLines4501143161();
        createOrderLines4501143162();
        createOrderLines4501105041();
        createOrderLines4501105045();
        createOrderLines4501105058();
    }

    private void createOrderLines4501143161() {
        createOrderLine("4501143161", 402608L, 1, "4330657A", "4743324194395", "SVERIGE VIT 8X21 FORMPRESSAD BASIC", 100, LocalDate.of(2025, 8, 25));
        createOrderLine("4501143161", 402608L, 2, "4331460A", "4743324195224", "SLÄT KOMPAKT VIT DÖRR M9X21", 20, LocalDate.of(2025, 8, 25));
        createOrderLine("4501143161", 402608L, 3, "9355252A", "4743324193718", "TREND KVADRAT 1SP VIT 9X21 1-SPEGEL", 60, LocalDate.of(2025, 10, 31));
        createOrderLine("4501143161", 402608L, 4, "4330318A", "4743324193954", "YD LUND VIT M10X21 HÖ MED KARM", 8, LocalDate.of(2025, 10, 31));
        createOrderLine("4501143161", 402608L, 5, "1459637", "4743324209747", "YD ODENSE VIT 10x21 HÖ MED KARM ED60-850", 16, LocalDate.of(2025, 10, 31));
        createOrderLine("4501143161", 402608L, 6, "1459632", "4743324209679", "YD ODENSE VIT 9x20 VÄ MED KARM ED60-850", 8, LocalDate.of(2025, 10, 31));
        createOrderLine("4501143161", 402608L, 7, "4330300A", "4743324193770", "FINLAND VIT 9X20 FORMPRESSAD BASIC", 25, LocalDate.of(2025, 8, 25));
        createOrderLine("4501143161", 402608L, 8, "4331452A", "4743324195149", "PROF KARM VIT M9X21 - 93 MM KF, MED TÄTNINGSLIST", 40, LocalDate.of(2025, 8, 25));
    }

    private void createOrderLines4501143162() {
        createOrderLine("4501143162", 402574L, 1, "4339135A", "4743324194159", "ALT 2. HANDTAG HÖGER FÖNSTERDÖRR", 8, LocalDate.of(2025, 10, 31));
        createOrderLine("4501143162", 402574L, 2, "1245665", "4743324195699", "VDF1 SIDOHÄNGT 2-GL VIT 6X8 V", 5, LocalDate.of(2025, 8, 25));
        createOrderLine("4501143162", 402574L, 3, "1246483", "4743324195750", "VHE VRID 3-GL VIT 14X13", 15, LocalDate.of(2025, 8, 25));
        createOrderLine("4501143162", 402574L, 4, "1245666", "4743324195712", "VDF1 SIDOHÄNGT 2-GL VIT 10X12", 5, LocalDate.of(2025, 8, 25));
        createOrderLine("4501143162", 402574L, 5, "1245514", "4743324193596", "VHE VRID 3-GL VIT 11x11", 15, LocalDate.of(2025, 8, 25));
        createOrderLine("4501143162", 402574L, 6, "1246425", "4743324193459", "VDF1 SIDOHÄNGT 2-GL VIT 8x12V", 10, LocalDate.of(2025, 8, 25));
        createOrderLine("4501143162", 402574L, 7, "1246330", "4743324196030", "Fönsterdörr 3-HELGLAS VHDSAL, 9x21 V", 5, LocalDate.of(2025, 10, 31));
        createOrderLine("4501143162", 402574L, 8, "1245685", "4743324193206", "VKF1 SIDOHÄNGT 1+1 OBEH 5x5V", 20, LocalDate.of(2025, 10, 31));
        createOrderLine("4501143162", 402574L, 9, "1246602", "4743324193428", "VDF1 SIDOHÄNGT 2-GL  VIT 6x6V", 5, LocalDate.of(2025, 8, 25));
        createOrderLine("4501143162", 402574L, 10, "1246484", "4743324193688", "FÖNSTERDÖRR VHED VIT 9x21/13 H 3-GLAS TRÄ", 10, LocalDate.of(2025, 10, 31));
        createOrderLine("4501143162", 402574L, 11, "1245702", "4743324193275", "VKF1 ÖVERHÄNGT 1+1 OBEH 8x5", 5, LocalDate.of(2025, 10, 31));
        createOrderLine("4501143162", 402574L, 12, "1245670", "4743324193503", "VDF2 SIDOH.2-GL VIT 10x12 2-LUFT", 10, LocalDate.of(2025, 10, 31));
        createOrderLine("4501143162", 402574L, 13, "1245565", "4743324194845", "FÖNSTERDÖRR HG VIT VHED 9X21 V HELGLAS TRÄ 3-GLAS", 5, LocalDate.of(2025, 8, 25));
        createOrderLine("4501143162", 402574L, 14, "1245701", "4743324193541", "VHE VRID 3-GL VIT 9x12", 15, LocalDate.of(2025, 8, 25));
    }

    private void createOrderLines4501105041() {
        createOrderLine("4501105041", 399147L, 1, "1459637", "4743324209747", "YD ODENSE VIT 10x21 HÖ MED KARM ED60-850", 8, LocalDate.of(2025, 7, 15));
        createOrderLine("4501105041", 399147L, 2, "1459626", "4743324208054", "YD RIBE 9x20 VÄ MED KARM ED60-140", 24, LocalDate.of(2025, 7, 15));
        createOrderLine("4501105041", 399147L, 3, "9355251A", "4743324193701", "TREND KVADRAT 1SP VIT 8X21 1-SPEGEL", 40, LocalDate.of(2025, 7, 15));
        
        createOrderLine("4501105041", 399148L, 1, "4339135A", "4743324194159", "ALT 2. HANDTAG HÖGER FÖNSTERDÖRR", 12, LocalDate.of(2025, 7, 15));
        createOrderLine("4501105041", 399148L, 2, "4339136A", "4743324194166", "ALT 2. HANDTAG VÄNSTER FÖNSTERDÖRR", 8, LocalDate.of(2025, 7, 15));
        createOrderLine("4501105041", 399148L, 3, "1246480", "4743324195682", "VDF1 SIDOHÄNGT 2-GL VIT 5X5 V", 20, LocalDate.of(2025, 7, 15));
        createOrderLine("4501105041", 399148L, 4, "1246602", "4743324193428", "VDF1 SIDOHÄNGT 2-GL  VIT 6x6V", 5, LocalDate.of(2025, 7, 15));
        createOrderLine("4501105041", 399148L, 5, "1246487", "4743324193435", "VDF1 SIDOHÄNGT 2-GL VIT 8x8V", 10, LocalDate.of(2025, 7, 15));
        createOrderLine("4501105041", 399148L, 6, "1245710", "4743324193497", "VDF2 SIDOH.2-GL VIT 10x10 2-LUFT", 20, LocalDate.of(2025, 7, 15));
        createOrderLine("4501105041", 399148L, 7, "1246417", "4743324194319", "VHE RUNT FAST 3-GL VIT, M6 580mm", 10, LocalDate.of(2025, 7, 15));
        createOrderLine("4501105041", 399148L, 8, "1246484", "4743324193688", "FÖNSTERDÖRR VHED VIT 9x21/13 H 3-GLAS TRÄ", 5, LocalDate.of(2025, 7, 15));
        
        createOrderLine("4501105041", 399149L, 1, "4330925A", "4743324194807", "BRANDDÖRR EI30 VIT 10X21/92 VÄ MED KARM", 8, LocalDate.of(2025, 7, 15));
    }

    private void createOrderLines4501105045() {
        createOrderLine("4501105045", 399127L, 1, "4355115A", "4743324192902", "SLÄT VIT DÖRR 7X21 CELL", 50, LocalDate.of(2025, 7, 15));
        createOrderLine("4501105045", 399127L, 2, "4355116A", "4743324192919", "SLÄT VIT DÖRR 8X21 CELL", 50, LocalDate.of(2025, 7, 15));
        createOrderLine("4501105045", 399127L, 3, "4332041A", "4743324195408", "FINLAND KOMPAKT VIT DÖRR M8X21", 20, LocalDate.of(2025, 7, 15));
        createOrderLine("4501105045", 399127L, 4, "4332042A", "4743324195415", "FINLAND KOMPAKT VIT DÖRR M9X21", 100, LocalDate.of(2025, 7, 15));
        createOrderLine("4501105045", 399127L, 5, "4355150A", "4743324193176", "FINLAND VIT 7X21 FORMPRESSAD BASIC", 100, LocalDate.of(2025, 7, 15));
        createOrderLine("4501105045", 399127L, 6, "4355151A", "4743324193183", "FINLAND VIT 8X21 FORMPRESSAD BASIC", 150, LocalDate.of(2025, 7, 15));
        createOrderLine("4501105045", 399127L, 7, "4355152A", "4743324193190", "FINLAND VIT 9X21 FORMPRESSAD BASIC", 50, LocalDate.of(2025, 7, 15));
        createOrderLine("4501105045", 399127L, 8, "4330658A", "4743324194401", "SVERIGE VIT 9X21 FORMPRESSAD BASIC", 25, LocalDate.of(2025, 7, 15));
        createOrderLine("4501105045", 399127L, 9, "9355251A", "4743324193701", "TREND KVADRAT 1SP VIT 8X21 1-SPEGEL", 20, LocalDate.of(2025, 7, 15));
        createOrderLine("4501105045", 399127L, 10, "4355161A", "4743324194128", "GÅNGJÄRN DÖRR 6540 JVA", 100, LocalDate.of(2025, 7, 15));
        createOrderLine("4501105045", 399127L, 11, "4355162A", "4743324194111", "GÅNGJÄRN KARM 6540 JVA", 200, LocalDate.of(2025, 7, 15));
        createOrderLine("4501105045", 399127L, 12, "4335135A", "4743324193978", "PLASTLOCK 16MM VIT", 200, LocalDate.of(2025, 7, 15));
        createOrderLine("4501105045", 399127L, 13, "4355164A", "4743324194142", "TÄTNINGSLIST YD 7M GRÅ", 10, LocalDate.of(2025, 7, 15));
    }

    private void createOrderLines4501105058() {
        createOrderLine("4501105058", 399126L, 1, "4355127A", "4743324193022", "VIT KARM 7X21 - 68MM", 40, LocalDate.of(2025, 7, 15));
        createOrderLine("4501105058", 399126L, 2, "4355134A", "4743324193084", "VIT KARM 7X21 - 93MM", 200, LocalDate.of(2025, 7, 15));
        createOrderLine("4501105058", 399126L, 3, "4355125A", "4743324193008", "VIT KARM 8X20 - 68MM", 40, LocalDate.of(2025, 7, 15));
        createOrderLine("4501105058", 399126L, 4, "4355129A", "4743324193046", "VIT KARM 9X21 - 68MM", 200, LocalDate.of(2025, 7, 15));
        createOrderLine("4501105058", 399126L, 5, "4339071A", "4743324196047", "VIT KARM M7X21 - 118 MM", 96, LocalDate.of(2025, 7, 15));
    }

    private void createOrderLine(String poNumber, Long orderNum, int line, String productCode, String eanCode, String productName, int qty, LocalDate deliveryDate) {
        OrderLine orderLine = new OrderLine();
        orderLine.setOrderNum(orderNum);
        orderLine.setLine(line);
        orderLine.setProductCode(productCode);
        orderLine.setEanCode(eanCode);
        orderLine.setProductName(productName);
        orderLine.setQty(new BigDecimal(qty));
        orderLine.setDeliveryDate(deliveryDate);
        orderLine.setPoNumber(poNumber);
        orderLineRepository.save(orderLine);
    }
}
