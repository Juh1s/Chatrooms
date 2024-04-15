package sof3.hh.chatroom.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sof3.hh.chatroom.domain.UserRepository;
import sof3.hh.chatroom.domain.User;

@Service
 public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private  UserRepository repository;
    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
    {   
        User curruser = repository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(), 
            AuthorityUtils.createAuthorityList(curruser.getRole()));
        return user;
    }   
}
