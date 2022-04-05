package library.mapper;

import library.entity.MyUser;
import library.rest.model.MyUserDTO;

public class MyUserMapper {

    public static MyUser fromDtoToEntity(MyUserDTO myUserDTO){
        return new MyUser(myUserDTO.getUsername(), myUserDTO.getPassword(), myUserDTO.isAccountNonExpired(),
                myUserDTO.isAccountNonLocked(), myUserDTO.isCredentialsNonExpired(), myUserDTO.isEnabled(),
                myUserDTO.getFullName(), myUserDTO.getContactDetails(), myUserDTO.getEmail(), myUserDTO.getRoles(),
                myUserDTO.getBooks(), myUserDTO.getPasswordConfirm());
    }

    public static MyUserDTO fromEntityToDTO(MyUser myUser){
        return new MyUserDTO(myUser.getId(), myUser.getUsername(), myUser.getFullName(), myUser.getContactDetails(),
                myUser.getEmail(), myUser.getBooks());
    }

}
