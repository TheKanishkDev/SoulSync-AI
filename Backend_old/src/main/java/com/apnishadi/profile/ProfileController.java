package com.apnishadi.profile;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.apnishadi.connection.InterestRequestRepository;
import com.apnishadi.connection.ConversationRepository;
import com.apnishadi.connection.ChatMessageRepository;
import com.apnishadi.auth.AccountRepository;


import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {
	private final ProfileRepository profileRepository;
	private final InterestRequestRepository interestRequestRepository;
	private final ConversationRepository conversationRepository;
	private final ChatMessageRepository chatMessageRepository;
	private final AccountRepository accountRepository;

	public ProfileController(
			ProfileRepository profileRepository,
			InterestRequestRepository interestRequestRepository,
			ConversationRepository conversationRepository,
			ChatMessageRepository chatMessageRepository,
			AccountRepository accountRepository
	) {
		this.profileRepository = profileRepository;
		this.interestRequestRepository = interestRequestRepository;
		this.conversationRepository = conversationRepository;
		this.chatMessageRepository = chatMessageRepository;
		this.accountRepository = accountRepository;
	}
	@GetMapping
	public List<Profile> searchProfiles(
			@RequestParam(required = false) String city,
			@RequestParam(required = false) String religion,
			@RequestParam(required = false) String gender
	) {
		return profileRepository.search(blankToNull(city), blankToNull(religion), blankToNull(gender));
	}

	@GetMapping("/{id}")
	public Profile getProfile(@PathVariable Long id) {
		return profileRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Profile not found"));
	}

	@PostMapping
	public Profile createProfile(@Valid @RequestBody Profile profile) {
		profile.setId(null);
		return profileRepository.save(profile);
	}
	@org.springframework.web.bind.annotation.PutMapping("/{id}")
	public Profile updateProfile(
			@PathVariable Long id,
			@Valid @RequestBody Profile updatedProfile
	) {
		Profile existing = profileRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Profile not found"));

		existing.setFullName(updatedProfile.getFullName());
		existing.setAge(updatedProfile.getAge());
		existing.setGender(updatedProfile.getGender());
		existing.setReligion(updatedProfile.getReligion());
		existing.setCity(updatedProfile.getCity());
		existing.setProfession(updatedProfile.getProfession());
		existing.setAbout(updatedProfile.getAbout());
		existing.setHeightCm(updatedProfile.getHeightCm());
		existing.setState(updatedProfile.getState());
		existing.setCommunity(updatedProfile.getCommunity());
		existing.setMotherTongue(updatedProfile.getMotherTongue());
		existing.setEducation(updatedProfile.getEducation());
		existing.setAnnualIncome(updatedProfile.getAnnualIncome());
		existing.setDiet(updatedProfile.getDiet());
		existing.setSmoking(updatedProfile.getSmoking());
		existing.setDrinking(updatedProfile.getDrinking());
		existing.setFamilyType(updatedProfile.getFamilyType());
		existing.setWantsChildren(updatedProfile.getWantsChildren());
		existing.setRelocation(updatedProfile.getRelocation());
		existing.setInterests(updatedProfile.getInterests());
		existing.setLifeGoals(updatedProfile.getLifeGoals());
		existing.setPartnerExpectations(updatedProfile.getPartnerExpectations());

		return profileRepository.save(existing);
	}

	@org.springframework.transaction.annotation.Transactional
	@org.springframework.web.bind.annotation.DeleteMapping("/{id}")
	public void deleteProfile(@PathVariable Long id)  {

		Profile profile = profileRepository.findById(id)
				.orElseThrow(() ->
						new ResponseStatusException(NOT_FOUND, "Profile not found"));

		chatMessageRepository.deleteAllByProfileId(id);

		conversationRepository.deleteAllByProfileId(id);

		interestRequestRepository.deleteAllSentRequests(id);
		interestRequestRepository.deleteAllReceivedRequests(id);

		accountRepository.deleteByProfileId(id);

		profileRepository.delete(profile);
	}

	private static String blankToNull(String value) {
		return value == null || value.isBlank() ? null : value.trim();
	}
}
