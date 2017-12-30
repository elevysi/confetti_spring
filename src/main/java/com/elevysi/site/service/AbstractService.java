package com.elevysi.site.service;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.elevysi.site.entity.Profile;
import com.elevysi.site.entity.Publication;
import com.elevysi.site.security.ActiveUser;


@Service
public abstract class AbstractService {
	
	@Autowired
	private PublicationService publicationService;
	
	@Value("${upload.img.path}")
	protected String avatarUploadPath;
	
	@Value("${ds}")
	protected String ds;
	
	/**
     * Returns a new object which specifies the the wanted result page.
     * @param pageIndex The index of the wanted result page
     * @return
     */
    protected Pageable constructPageSpecification(int pageIndex, int limit,  String sortField, String sortDirection) {
    	Pageable pageSpecification = new PageRequest(pageIndex, limit, sortByField(sortField, sortDirection));
        return pageSpecification;
    }
 
 
    /**
     * Returns a Sort object which sorts persons in ascending order by using the last name.
     * @return
     */
    private Sort sortByField(String sortField, String sortDirection) {
    	
    	if(sortDirection.equalsIgnoreCase("desc")){
    		return new Sort(Sort.Direction.DESC, sortField);
    	}else return new Sort(Sort.Direction.ASC, sortField);
        
    }
    
    protected Publication savePublication(Profile profile, String toSlug){
    	
    	/**
		 * Create Publication for this post
		 */
		Publication publication = new Publication();
		
		Date now = new Date();
		publication.setCreated(now);
		publication.setModified(now);
		publication.setPublicPublication(true);
		publication.setProfile(profile);
		String slug = publicationService.toSlug(toSlug);
		if(slug != null){
			publication.setFriendlyUrl(slug);
		}
    	return publicationService.savePublication(publication);
    	
    }
    
    protected Publication saveEditedPublication(int id, String toSlug){
    	return publicationService.updateItemPublicationWithSlug(id, toSlug);
    }
    
    
    public Profile getActiveProfile(){
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	boolean isAuthenticated =  this.isAuthed();
    	
    	if(auth != null && isAuthenticated){
			ActiveUser activeUser = (ActiveUser)auth.getPrincipal();
			Profile actingProfile = activeUser.getActiveProfile();
			if(actingProfile != null){
				return actingProfile;
			}
		}
    	
    	return null;
    }
    
    public ActiveUser getActiveUser(){
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	boolean isAuthenticated =  this.isAuthed();
    	
    	if(auth != null && isAuthenticated){
			return  (ActiveUser)auth.getPrincipal();
		}
    	
    	return null;
    }
   
    
    public boolean isAuthed(){
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return true;
		}
    	
    	return false;
    }
    
    public int getStart(int pageNo, int maxResults){
    	
    	return pageNo == maxResults * (pageNo - 1) ? 1 : maxResults * (pageNo - 1) + 1;
    	
    }
    
    public boolean isAdmin(){
		Profile active = getActiveProfile();
		if(active !=null){
//			List<Role>
//			if(active.getUser().getRoles()){
//				return true;
//			}
		}
    	return true;
	}
    
   /**
    * In mathematics, the factorial of a non-negative integer n, denoted by n!, is the product of all positive integers less than or equal to n
    * E.g. 5! = 5 * 4 * 3 * 2 * 1
    * @param n The number we wish to compute the factorial for
    * @return The factorial value
    */
    public int factorialComputation(int n){
		while(n > 0){
			return n * factorialComputation(( n - 1));
		}
		
		return n;
	}
	
    
   /**
    * 
    * In mathematics, the Fibonacci numbers are the numbers in the following integer sequence, called the Fibonacci sequence
	* and characterized by the fact that every number after the first two is the sum of the two preceding ones
    * 1 , 1 , 2 , 3 , 5 , 8 , 13 , 21 , 34 , 55 , 89 , 144
    * @param n --> the position of the number we are seeking for
    * @return the number that occupies the position n in the Fibonacci Sequence 
    */
	public int fibonacciElement(int n){
		if(n == 0 ) return 0;
		else if(n == 1) return 1;
		else return fibonacciElement(n -1) + fibonacciElement(n -2); //element at nth position is the sum of the 2 preceding it
	}
	
	/**
	 * 
	 * @param collectionToSort The unsorted collection that we wish to sort
	 * @return The sorted collection taken as the input
	 */
	public int[] sortAndMerge(int[] collectionToSort){
		
		int length = collectionToSort.length;
		if(length > 1){
			
			//We want first to divide into two
			int middle = length / 2; // for an odd value, java will make e.g. 3.5 to 3
			int[] leftHalf = new int[middle];
			int[] rightHalf = new int[length - middle];
			
			//System.arraycopy(Object[] src, int srcStartIndex, Object[] dest, int dstStartIndex, int lengthOfCopiedIndices);
			
			System.arraycopy(collectionToSort, 0, leftHalf, 0, middle);
			System.arraycopy(collectionToSort, middle, rightHalf, 0, rightHalf.length);
			
			//Recursive calling of the obtained 
			sortAndMerge(leftHalf);
			sortAndMerge(rightHalf);
			
			int i = 0; //index for the collectionToSort
			int j = 0; //index for the leftArray
			int k = 0; //index for the rightArray
			
			//This is the merge sort step
			//The loop will execute until one the halves has emptied all its elements at which moment we push the remaining element of the other half to the collection to sort
			while(leftHalf.length != j && rightHalf.length != k){
				if(leftHalf[j] < rightHalf[k]){
					collectionToSort[i] = leftHalf[j];
					j++;
				}else if(rightHalf[k] < leftHalf[j]){
					collectionToSort[i] = rightHalf[k];
					k++;
				}
				
				i++;
			}
			
			//Handle whichever half is left with elements
			if(leftHalf.length != j){
				System.arraycopy(leftHalf, j, collectionToSort, i, leftHalf.length - j);
			}else if(rightHalf.length != k){
				System.arraycopy(rightHalf, k, collectionToSort, i, rightHalf.length - k);
			}
		}
		
		return collectionToSort;
	}
	
	//Ascending order
	public int[] bubleSort(int[] collection){
		int tmp;
		for(int outer = 0; outer < collection.length; outer++){
			for(int inner  = 0; inner < collection.length; inner ++){
				if(collection[outer] < collection[inner]){
					tmp = collection[inner];
					collection[inner] = collection[outer];
					collection[outer] = tmp;
				}
			}
			
		}
		
		return collection;
	}
	
	//Ascending order
	public int[] insertSort(int[] collection){
		
		for(int i = 0; i < collection.length; i ++){
			
			int k = i - 1; //We started from zero, check for the elements on the left if there is smaller 
			int value = collection[i];
			
			while(k >= 0 && collection[k] > value){
					collection[k] = collection[k -1];
				
				k--; //Decrement j
			}
			collection[k + 1] = collection[i]; 
		}
		return collection;
	}

}
