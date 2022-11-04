package com.software.swastik.project.swastik.connect;

import com.software.swastik.project.swastik.connect.configurations.AppConstants;
import com.software.swastik.project.swastik.connect.entities.Role;
import com.software.swastik.project.swastik.connect.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SwastikConnectApplication implements CommandLineRunner
{
	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args)
	{
		SpringApplication.run(SwastikConnectApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception
	{
		try
		{
			Role role1 = new Role();
			role1.setRoleId(AppConstants.NORMAL_USER);
			role1.setRoleName(AppConstants.NORMAL_USER_NAME);
			Role role2=new Role();
			role2.setRoleId(AppConstants.ADMIN_USER);
			role2.setRoleName(AppConstants.ADMIN_USER_NAME);
			List<Role> roles = List.of(role1, role2);
			List<Role> result = this.roleRepository.saveAll(roles);
		}
		catch(Exception e)
		{
			throw new Exception("Role is Not Set");
		}
	}
}
