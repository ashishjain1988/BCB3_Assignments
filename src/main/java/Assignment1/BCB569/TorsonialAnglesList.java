package Assignment1.BCB569;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class TorsonialAnglesList.
 *
 * @author Ashish Jain
 */
public class TorsonialAnglesList {

	/** The Constant chi_atoms. */
	public static final Map<String, Map<String, List<String>>> chi_atoms;
	static {
		Map<String, Map<String, List<String>>> temp_hi_atoms = new HashMap<String, Map<String,List<String>>>();
		Map<String, List<String>> atoms = new HashMap<String, List<String>>();
		atoms.put("ARG", Arrays.asList("N","CA","CB","CG"));
		atoms.put("ASN", Arrays.asList("N","CA","CB","CG"));
		atoms.put("ASP", Arrays.asList("N","CA","CB","CG"));
		atoms.put("CYS", Arrays.asList("N","CA","CB","SG"));
		atoms.put("GLN", Arrays.asList("N","CA","CB","CG"));
		atoms.put("GLU", Arrays.asList("N","CA","CB","CG"));
		atoms.put("HIS", Arrays.asList("N","CA","CB","CG"));
		atoms.put("ILE", Arrays.asList("N","CA","CB","CG1"));
		atoms.put("LEU", Arrays.asList("N","CA","CB","CG"));
		atoms.put("LYS", Arrays.asList("N","CA","CB","CG"));
		atoms.put("MET", Arrays.asList("N","CA","CB","CG"));
		atoms.put("PHE", Arrays.asList("N","CA","CB","CG"));
		atoms.put("PRO", Arrays.asList("N","CA","CB","CG"));
		atoms.put("SER", Arrays.asList("N","CA","CB","OG"));
		atoms.put("THR", Arrays.asList("N","CA","CB","OG1"));
		atoms.put("TRP", Arrays.asList("N","CA","CB","CG"));
		atoms.put("TYR", Arrays.asList("N","CA","CB","CG"));
		atoms.put("VAL", Arrays.asList("N","CA","CB","CG1"));
		temp_hi_atoms.put("chi1", atoms);
		
		atoms = new HashMap<String, List<String>>();
		atoms.put("VAL", Arrays.asList("N", "CA", "CB", "CG2"));
		temp_hi_atoms.put("altchi1", atoms);
		
		atoms = new HashMap<String, List<String>>();
		atoms.put("ARG", Arrays.asList("CA", "CB", "CG", "CD"));
		atoms.put("ASN", Arrays.asList("CA", "CB", "CG", "OD1"));
		atoms.put("ASP", Arrays.asList("CA", "CB", "CG", "OD1"));
		atoms.put("GLN", Arrays.asList("CA", "CB", "CG", "CD"));
		atoms.put("GLU", Arrays.asList("CA", "CB", "CG", "CD"));
		atoms.put("HIS", Arrays.asList("CA", "CB", "CG", "ND1"));
		atoms.put("ILE", Arrays.asList("CA", "CB", "CG1", "CD1"));
		atoms.put("LEU", Arrays.asList("CA", "CB", "CG", "CD1"));
		atoms.put("LYS", Arrays.asList("CA", "CB", "CG", "CD"));
		atoms.put("MET", Arrays.asList("CA", "CB", "CG", "SD"));
		atoms.put("PHE", Arrays.asList("CA", "CB", "CG", "CD1"));
		atoms.put("PRO", Arrays.asList("CA", "CB", "CG", "CD"));
		atoms.put("TRP", Arrays.asList("CA", "CB", "CG", "CD1"));
		atoms.put("TYR", Arrays.asList("CA", "CB", "CG", "CD1"));
		temp_hi_atoms.put("chi2", atoms);
		
		atoms = new HashMap<String, List<String>>();
		atoms.put("ASP", Arrays.asList("CA", "CB", "CG", "OD2"));
		atoms.put("LEU", Arrays.asList("CA", "CB", "CG", "CD2"));
		atoms.put("PHE", Arrays.asList("CA", "CB", "CG", "CD2"));
		atoms.put("TYR", Arrays.asList("CA", "CB", "CG", "CD2"));
		temp_hi_atoms.put("altchi2", atoms);
		
		atoms = new HashMap<String, List<String>>();
		atoms.put("ARG", Arrays.asList("CB", "CG", "CD", "NE"));
		atoms.put("GLN", Arrays.asList("CB", "CG", "CD", "OE1"));
		atoms.put("GLU", Arrays.asList("CB", "CG", "CD", "OE1"));
		atoms.put("LYS", Arrays.asList("CB", "CG", "CD", "CE"));
		atoms.put("MET", Arrays.asList("CB", "CG", "SD", "CE"));
		temp_hi_atoms.put("chi3", atoms);
		
		atoms = new HashMap<String, List<String>>();
		atoms.put("ARG", Arrays.asList("CG", "CD", "NE", "CZ"));
		atoms.put("LYS", Arrays.asList("CG", "CD", "CE", "NZ"));
		temp_hi_atoms.put("chi4", atoms);
		
		atoms = new HashMap<String, List<String>>();
		atoms.put("ARG", Arrays.asList("CD", "NE", "CZ", "NH1"));
		temp_hi_atoms.put("chi5", atoms);
		
        chi_atoms = Collections.unmodifiableMap(temp_hi_atoms);
    }
}
