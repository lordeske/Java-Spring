    package com.eske.controller;

    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping
    public class HomeController {

        @GetMapping
        public ResponseEntity<String> homeController() {
            return ResponseEntity.status(HttpStatus.MULTI_STATUS).body("hello");
        }
    }
