package com.messenger.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.messenger.dao.UserDao;
import com.messenger.model.User;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

	@Override
	@Transactional
	public List<User> getAllUsers() {
		
		return userDao.get();
	}

	@Override
	@Transactional
	public User getUser(String username) {
		return userDao.get(username);
	}

	@Override
	@Transactional
	public void addUser(User user) {
		
		userDao.save(user);
		
	}


	@Override
	@Transactional
	public String deleteUser(String username) {
		
		return userDao.delete(username);
	}

	@Override
	@Transactional
	public void updateUser(User user) {
		userDao.updateUserDetails(user);
	}
	
	@Override
	@Transactional  
    public int store(MultipartFile file, String username, HttpSession session) {  
          
        Path rootLocation = Paths.get(session.getServletContext().getRealPath("/resources/images"));  
          
        System.out.println("rootLocation  ==  " + rootLocation);  
          
        User userDetail = this.getUser(username);  
           
         String[] nameExtension = Objects.requireNonNull(file.getContentType()).split("/");
  
         String profileImage = username + "." + nameExtension[1];  
              
         System.out.println("ProfileImage  :: " + profileImage);  
           
         if(userDetail.getUsername() != null )  
         {  
               
            if(userDetail.getProfileImage() == null || userDetail.getProfileImage().equals(" ") || userDetail.getProfileImage().equals(""))
            {  
                try  
                {  
                    Files.copy(file.getInputStream(),rootLocation.resolve(profileImage));  
                    int result = userDao.updateProfileImage(profileImage, username);    
                    if(result > 0)  
                        return result;  
                    else  
                        return -5;  
                }  
                catch(Exception exception)  
                {  
                    System.out.println("error while uploading image catch:: " + exception.getMessage());  
                    return -5;  
                }  
            }  
            else  
            {  
                try  
                {  
                    //Files.delete(rootLocation.resolve(profileImage));  
                      
                    Files.delete(rootLocation.resolve(userDetail.getProfileImage()));  
                      
                    Files.copy(file.getInputStream(),rootLocation.resolve(profileImage));  
                    int result = userDao.updateProfileImage(profileImage, username);    
                    if(result > 0)  
                        return result;  
                    else  
                        return -5;  
                }  
                catch(Exception exception)  
                {  
                    System.out.println("Error while uploading image when image is already Exists :: " + exception.getMessage());  
                    return -5;  
                }  
            }  
        }  
        else {  
            return 0;  
        }  
    }  
	

}
