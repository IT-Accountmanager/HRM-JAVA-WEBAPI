/*
 * package com.hrm.main.controllers;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.hrm.main.models.AuthenticateUserEntity; import
 * com.hrm.main.services.IAuthenticateUserService;
 * 
 * @RestController
 * 
 * @RequestMapping(value = "/Authenticate")
 * 
 * public class AuthenticateController {
 * 
 * @Autowired IAuthenticateUserService authService;
 * 
 * @PostMapping("Authenticate") public ResponseEntity<String>
 * authenticateUser(@RequestBody AuthenticateUserEntity request) { boolean
 * isAuthenticated = authService.authenticateUser(request);
 * 
 * if (isAuthenticated) { return new ResponseEntity<String>("Authenticated",
 * HttpStatus.OK); } else { return new
 * ResponseEntity<String>(" Not Authenticated", HttpStatus.BAD_REQUEST);
 * 
 * } }
 * 
 * }
 */