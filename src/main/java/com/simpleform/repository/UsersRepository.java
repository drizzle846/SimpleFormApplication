package com.simpleform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simpleform.model.UsersModel;
@Repository
public interface UsersRepository extends JpaRepository<UsersModel, Integer> {

	Optional<List<UsersModel>> findByFullnameAndMobile(String fullname, String mobile);
	
	Optional<UsersModel> findById(Integer id);


	Optional<List<UsersModel>> findUsersModelsByFullnameAndMobileAndEmailAndCity(
            String fullname, String mobile, String email, String city);

	Optional< List<UsersModel>> findByMobileAndEmailAndCity(String mobile, String email, String city);
	Optional< List<UsersModel>> findByFullnameAndEmailAndCity(String fullname, String email, String city);
	Optional< List<UsersModel>> findByFullnameAndMobileAndCity(String fullname, String mobile, String city);
	Optional< List<UsersModel>> findByFullnameAndMobileAndEmail(String fullname, String mobile, String email);
	Optional< List<UsersModel>> findByFullnameAndCity(String fullname, String city);
	Optional< List<UsersModel>> findByFullnameAndEmail(String fullname, String email);
	Optional< List<UsersModel>> findByMobileAndEmail(String mobile, String email);
	Optional< List<UsersModel>> findByMobileAndCity(String mobile, String city);
	Optional< List<UsersModel>> findByEmailAndCity(String email, String city);
	Optional< List<UsersModel>> findByEmail(String email);
	Optional< List<UsersModel>> findByCity(String city);
	Optional< List<UsersModel>> findByFullname(String fullname);
	Optional< List<UsersModel>> findByMobile(String mobile);










	
}
