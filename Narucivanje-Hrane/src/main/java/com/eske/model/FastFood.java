    package com.eske.model;

    import com.fasterxml.jackson.annotation.JsonIgnore;
    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.time.LocalDateTime;
    import java.util.ArrayList;
    import java.util.List;

    @Data
    @Entity
    @NoArgsConstructor
    @AllArgsConstructor

    public class FastFood {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private  Long fastfoodID;

        @OneToOne
        private User owner;


        private String fastFoodName;
        private String description;

        @OneToOne
        private Adresa fastFoodAdress;


        @Embedded
        private ContactInfo contactInfo;

        @ElementCollection
        @Column(length = 1000)
        private List<String> orderListImages;


        private LocalDateTime regDate;

        private  Boolean open;

        @OneToMany(mappedBy = "fastFood", cascade = CascadeType.ALL,  orphanRemoval = true)
        @JsonIgnore
        private List<Order> orders = new ArrayList<>();


        @OneToMany(mappedBy = "fastFood", cascade = CascadeType.ALL,  orphanRemoval = true)
        @JsonIgnore
        private List<Food> foods = new ArrayList<>();







    }
