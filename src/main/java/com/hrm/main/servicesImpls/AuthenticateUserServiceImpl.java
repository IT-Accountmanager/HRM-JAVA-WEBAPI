/*
 * package com.hrm.main.servicesImpls;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service; import
 * com.hrm.main.models.AuthenticateUserEntity; import
 * com.hrm.main.repositories.IAuthenticateUserRepository; import
 * com.hrm.main.services.IAuthenticateUserService;
 * 
 * @Service public class AuthenticateUserServiceImpl implements
 * IAuthenticateUserService {
 * 
 * @Autowired IAuthenticateUserRepository userRepo;
 * 
 * @Override public boolean authenticateUser(AuthenticateUserEntity userEntity)
 * { AuthenticateUserEntity user =
 * userRepo.findByUserIdAndPassword(userEntity.getEmailId(),
 * userEntity.getPassword()); return user != null; }
 * 
 * }
 */