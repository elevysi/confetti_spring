package com.elevysi.site.blog.test.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.elevysi.site.blog.BlogBootApplication;
import com.elevysi.site.blog.dao.PlayDAO;
import com.elevysi.site.blog.entity.Category;
import com.elevysi.site.blog.entity.Dossier;
import com.elevysi.site.blog.entity.Play;
import com.elevysi.site.blog.entity.PlayType;
import com.elevysi.site.blog.entity.Play_;
import com.elevysi.site.blog.entity.Post;
import com.elevysi.site.blog.entity.Publication;
import com.elevysi.site.blog.entity.PublicationStatus;
import com.elevysi.site.blog.entity.Upload;
import com.elevysi.site.blog.service.CategoryService;
import com.elevysi.site.blog.service.DossierService;
import com.elevysi.site.blog.service.PlayTypeService;
import com.elevysi.site.blog.service.PublicationStatusService;
import com.elevysi.site.commons.dto.ProfileDTO;
import com.elevysi.site.commons.pojo.Page;
import com.elevysi.site.commons.pojo.Page.SortDirection;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={BlogBootApplication.class})
@Transactional
public class PlayDAOTest {

	private Play play;
	private final String testContent =  "Far far away, behind the word mountainsTesting_, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean. A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country, in which roasted parts of sentences fly into your mouth. Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life One day however a small line of blind text by the name of Lorem Ipsum decided to leave for the far World of Grammar. The Big Oxmox advised her not to do so, because there were thousands of bad Commas, wild Question Marks and devious Semikoli, but the Little Blind Text didn’t listen. She packed her seven versalia, put her initial into the belt and made herself on the way. When she reached the first hills of the Italic Mountains, she had a last view back on the skyline of her hometown Bookmarksgrove, the headline of Alphabet Village and the subline of her own road, the Line Lane. Pityful a rethoric question ran over her cheek, then";
	private final String testTitle = "UnreplicatableTestTitle123";
	private ProfileDTO profile;
	private Publication publication;
	private List<Play> plays = new ArrayList<Play>();
	
	@Autowired
	private PlayDAO playDAO;
	
	@Autowired
	private PlayTypeService playTypeService;
	
	@Autowired
	private DossierService dossierService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PublicationStatusService publicationStatusService;
	
	
	
	@Before
	public void init(){
		
		profile = new ProfileDTO();
		profile.setId(1);
		profile.setName("Tester");
		
		play = new Play();
		play.setTitle(testTitle);
		play.setEmbeddedUrl("Embedded URL");
		play.setUrl("Test URL");
		play.setProfileID(1);
		play.setDescription(testContent);
		
		Date now = new Date();
		play.setCreated(now);
		play.setModified(now);
		
		PlayType playType = playTypeService.findByID(1);
		play.setPlayType(playType);
		
		
		publication = new Publication();
		publication.setFriendlyUrl("Test Slug");
		
		publication.setProfile(profile);
		publication.setProfileName(profile.getName());
		
		Set<Category> categories = new HashSet<Category>();
		Category cat1 = categoryService.findByID(1);
		Category cat2 = categoryService.findByID(2);
		categories.add(cat1);
		categories.add(cat2);
		
		Set<Dossier> dossiers = new HashSet<Dossier>();
		Dossier d1 = dossierService.findByID(19);
		Dossier d2 = dossierService.findByID(20);
		dossiers.add(d1);
		dossiers.add(d2);
		
		
		PublicationStatus postStatus = publicationStatusService.findPublishedPostStatus();
		publication.setPublicationStatus(postStatus);
		publication.setCategories(categories);
		publication.setDossiers(dossiers);
		
		play.setPublication(publication);
	}
	
	@Test
	public void testSavePost() {
		
		/**
		 * Test save
		 */
		
		Play savedPlay = playDAO.save(play);
		assertEquals(play.getTitle(), savedPlay.getTitle());
		assertEquals(play.getEmbeddedUrl(), savedPlay.getEmbeddedUrl());
		assertEquals(play.getUrl(), savedPlay.getUrl());
		assertEquals(play.getProfileID(), savedPlay.getProfileID());
		assertEquals(play.getDescription(), savedPlay.getDescription());
		assertEquals(play.getPlayType(), savedPlay.getPlayType());
		assertEquals(publication.getDossiers(), play.getPublication().getDossiers());
		assertEquals(publication.getCategories(), play.getPublication().getCategories());
		assertEquals(publication.getProfile(), play.getPublication().getProfile());
		assertEquals(publication.getPublicationStatus(), play.getPublication().getPublicationStatus());
		
		/**
		 * Test Find
		 */
		Play foundPlay = playDAO.findByID(savedPlay.getId());
		assertEquals(savedPlay, foundPlay);
		assertEquals(savedPlay.getTitle(), foundPlay.getTitle());
		assertEquals(savedPlay.getEmbeddedUrl(), foundPlay.getEmbeddedUrl());
		assertEquals(savedPlay.getUrl(), foundPlay.getUrl());
		assertEquals(savedPlay.getProfileID(), foundPlay.getProfileID());
		assertEquals(savedPlay.getDescription(), foundPlay.getDescription());
		assertEquals(savedPlay.getPlayType(), foundPlay.getPlayType());
		
		assertEquals(foundPlay.getPublication().getFriendlyUrl(), savedPlay.getPublication().getFriendlyUrl());
		assertEquals(foundPlay.getPublication().getDossiers(), savedPlay.getPublication().getDossiers());
		assertEquals(foundPlay.getPublication().getCategories(), savedPlay.getPublication().getCategories());
		assertEquals(foundPlay.getPublication().getPublicationStatus(), savedPlay.getPublication().getPublicationStatus());
		assertEquals(foundPlay.getPublication().getProfile(), savedPlay.getPublication().getProfile());
		
		Play nonExistingFoundPlay = playDAO.findByID(-2);
		assertEquals(null, nonExistingFoundPlay);
		
		/**
		 * Test Pages
		 */
		Page page = playDAO.buildOffsetPage(1, 1, Play_.created, SortDirection.DESC);
		plays.add(savedPlay);
		
		List<Play> foundPlays = playDAO.getPlays(page);
		assertEquals(plays, foundPlays);
		
		List<Play> latestForProfile = playDAO.getAllLatestForProfile(profile, page);
		assertEquals(plays, latestForProfile);
		
		/**
		 * Test Search
		 */
		assertEquals(plays, playDAO.searchByTerm(testTitle)); //Title
		assertEquals(plays, playDAO.searchByTerm(testContent)); //Description
		assertEquals(new ArrayList<Play>(), playDAO.searchByTerm("abdcNonExistant"));
		
		
		/**
		 * Test Delete
		 */
		playDAO.delete(savedPlay.getId());
		assertEquals(null, playDAO.findByID(savedPlay.getId()));
	}
}
