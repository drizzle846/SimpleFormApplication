package com.simpleform.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpleform.model.UsersModel;
import com.simpleform.qrcode.QRCodeGenerator;
import com.simpleform.repository.UsersRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class UsersService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	public UsersModel registerUser(String fullname, String city, String mobile, String email) {
		if (fullname != null && mobile != null && email != null) {if(usersRepository.findByFullnameAndMobile(fullname, mobile).isPresent())
		{System.out.println("Duplicate login");
		return null;}
		else {UsersModel usersModel = new UsersModel();
		usersModel.setFullname(fullname);
		usersModel.setEmail(email);
		usersModel.setMobile(mobile);
		usersModel.setCity(city);
		return usersRepository.save(usersModel);
			
		}
			
		}
		else {
			return null;
		}
		
		
	}
	public UsersModel updateuser(UsersModel usersModel, Integer id) {
	    UsersModel user = usersRepository.findById(id).get();
	    if (usersModel != null) {
	        String fullname = usersModel.getFullname();
	        String city = usersModel.getCity();
	        String mobile = usersModel.getMobile();
	        String email = usersModel.getEmail();
            
	        if (fullname != null && mobile != null && email != null) {
	            user.setFullname(fullname);
	            user.setCity(city);
	            user.setEmail(email);
	            user.setMobile(mobile);
	            return usersRepository.save(user);
	        } else {
	            System.out.println("Error");
	            return null;
	        }
	    } else {
	        System.out.println("User not found");
	        return null;
	    }
	}

		
		
	public List<UsersModel> authenticate(String fullname, String mobile) {
		return usersRepository.findByFullnameAndMobile(fullname, mobile).orElse(null);
	}
	public UsersModel find(Integer id) {
		return usersRepository.findById(id).get();
	}
	
	public void save(UsersModel user) {
	    usersRepository.save(user);
	}
	 public List<UsersModel> getAllEntries() {
	        return usersRepository.findAll();
	    }
	 
	public List<UsersModel> searchEntries(String fullname, String mobile, String email, String city) {
		if (fullname ==null && mobile != null && email != null && city != null) {
			return usersRepository.findByMobileAndEmailAndCity(mobile,email,city).get();
		}else {if (fullname !=null && mobile == null && email != null && city != null) {
			return usersRepository.findByFullnameAndEmailAndCity(fullname, email, city).get();
		}else {if(fullname !=null && mobile != null && email == null && city != null) {
			return usersRepository.findByFullnameAndMobileAndCity(fullname, mobile, city).get();
		}else {if(fullname !=null && mobile != null && email != null && city == null) {
			return usersRepository.findByFullnameAndMobileAndEmail(fullname, mobile, email).get();
		}else {if(fullname ==null && mobile == null && email != null && city != null) {
			return usersRepository.findByEmailAndCity(email, city).get();
		}else {if(fullname ==null && mobile != null && email == null && city != null) {
			return usersRepository.findByMobileAndCity(mobile, city).get();
		}else {if(fullname ==null && mobile != null && email != null && city == null) {
			return usersRepository.findByMobileAndEmail(mobile, email).get();
		}else {if(fullname !=null && mobile == null && email == null && city != null) {
			return usersRepository.findByFullnameAndCity(fullname, city).get();
		}else {if(fullname !=null && mobile != null && email == null && city == null) {
			return usersRepository.findByFullnameAndMobile(fullname, mobile).get();
		}else {if(fullname !=null && mobile == null && email != null && city == null) {
			return usersRepository.findByFullnameAndEmail(fullname, email).get();
		}else {if(fullname !=null && mobile == null && email == null && city == null) {
			return usersRepository.findByFullname(fullname).get();
		}else {if(fullname ==null && mobile != null && email == null && city == null) {
			return usersRepository.findByMobile(mobile).get();
		}else {if(fullname ==null && mobile == null && email != null && city == null) {
			return usersRepository.findByEmail(email).get();
		}else {if(fullname ==null && mobile == null && email == null && city != null) {
			return usersRepository.findByCity(city).get();
		}else {if ((fullname !=null && mobile != null && email != null && city != null)) {
			return usersRepository.findUsersModelsByFullnameAndMobileAndEmailAndCity(fullname, mobile, email, city).get();
		}else return null;
			
			
		}
			
		}
			
		}
			
		}
			
		}
		
			
		}
			
		}
			
		}
			
		}
			
		}
			
		}
			
		}
			
		}
			
		}

	    
	}
//	 @Autowired
//	 private EntityManager entityManager;
//
//	 public List<UsersModel> searchEntries(String fullname, String mobile, String email, String city) {
//	     CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//	     CriteriaQuery<UsersModel> query = cb.createQuery(UsersModel.class);
//	     Root<UsersModel> root = query.from(UsersModel.class);
//
//	     Predicate predicate = cb.conjunction();
//
//	     if (fullname != null) {
//	         predicate = cb.and(predicate, cb.equal(root.get("fullname"), fullname));
//	     }
//	     if (mobile != null) {
//	         predicate = cb.and(predicate, cb.equal(root.get("mobile"), mobile));
//	     }
//	     if (email != null) {
//	         predicate = cb.and(predicate, cb.equal(root.get("email"), email));
//	     }
//	     if (city != null) {
//	         predicate = cb.and(predicate, cb.equal(root.get("city"), city));
//	     }
//
//	     query.select(root).where(predicate);
//	     TypedQuery<UsersModel> typedQuery = entityManager.createQuery(query);
//	     return typedQuery.getResultList();
//
//	 }
	public List<UsersModel> searchEntriesByName(String fullname) {
		// TODO Auto-generated method stub
		return usersRepository.findByFullname(fullname).get();
	}
	public List<UsersModel> searchEntriesByCity(String city) {
		// TODO Auto-generated method stub
		return usersRepository.findByCity(city).get();
	}
	public List<UsersModel> searchEntriesByMobile(String mobile) {
		// TODO Auto-generated method stub
		return usersRepository.findByMobile(mobile).get();
	}
	public List<UsersModel> searchEntriesByEmail(String email) {
		// TODO Auto-generated method stub
		return usersRepository.findByEmail(email).get();
	}
	

}
