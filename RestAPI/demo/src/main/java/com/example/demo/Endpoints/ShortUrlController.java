// package com.example.demo.Endpoints;

// import java.nio.charset.StandardCharsets;

// import com.google.common.hash.Hashing;

// import org.apache.commons.validator.UrlValidator;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.redis.core.StringRedisTemplate;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RequestMapping("/rest/url")
// @RestController
// public class ShortUrlController {

//     @Autowired
//     private StringRedisTemplate redisTemplate;

//     @GetMapping("/{id}")
//     public String getUrl(@PathVariable final String id) {
//         final String url = redisTemplate.opsForValue().get(id);
//         return url;

//     }

//     @PostMapping
//     public String createUrl(@RequestBody final String url) {

//         final UrlValidator urlValidator = new UrlValidator();
//         if (urlValidator.isValid(url)) {
//             final String id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
//            System.out.println("id is "+id);
//            redisTemplate.opsForValue().set(id,url);
//            return id;

//         }
//         throw new RuntimeException();

//     }
    
// }
