package com.tconnect.entity;

import java.util.Base64;
import java.util.List;

import com.tconnect.dto.Certification;
import com.tconnect.dto.Experience;
import com.tconnect.dto.ProfileDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profiles")
public class Profile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String jobTitle;
	private String company;
	private String location;
	private String about;
	private byte[] picture; 
	private Long totalExp;
	private List<String> skills;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "profile_id") // Unidirectional relationship
	private List<Experience> experiences;
//	private List<Certification>certifications;
	@ElementCollection
	@CollectionTable(name = "user_certifications", joinColumns = @JoinColumn(name = "user_id"))
	private List<Certification> certifications;
	private List<Long>savedJobs;
	
	public ProfileDTO toDTO() {
		return new ProfileDTO(this.id, this.name, this.email, this.jobTitle, this.company, this.location, this.about, this.picture!=null?Base64.getEncoder().encodeToString(this.picture):null, this.totalExp, this.skills, this.experiences, this.certifications, this.savedJobs);
	}
}

