package Assignment1.BCB569;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TorsonialAnglesList {

	public static final Map<String, Map<String, List<String>>> chi_atoms;
	static {
		Map<String, Map<String, List<String>>> temp_hi_atoms = new HashMap<String, Map<String,List<String>>>();
		Map<String, List<String>> atoms = new HashMap<String, List<String>>();
		atoms.put("ARG", Arrays.asList("N","CA","CB","CG"));
		atoms.put("ASN", Arrays.asList("N","CA","CB","CG"));
		atoms.put("ASP", Arrays.asList("N","CA","CB","CG"));
		atoms.put("ARG", Arrays.asList("N","CA","CB","CG"));
		atoms.put("ARG", Arrays.asList("N","CA","CB","CG"));
		atoms.put("ARG", Arrays.asList("N","CA","CB","CG"));
		atoms.put("ARG", Arrays.asList("N","CA","CB","CG"));
		atoms.put("ARG", Arrays.asList("N","CA","CB","CG"));
		atoms.put("ARG", Arrays.asList("N","CA","CB","CG"));
		atoms.put("ARG", Arrays.asList("N","CA","CB","CG"));
		atoms.put("ARG", Arrays.asList("N","CA","CB","CG"));
		atoms.put("ARG", Arrays.asList("N","CA","CB","CG"));
		atoms.put("ARG", Arrays.asList("N","CA","CB","CG"));
		
		temp_hi_atoms.put("chi1", atoms);
        chi_atoms = Collections.unmodifiableMap(temp_hi_atoms);
    }
	/*chi_atoms = dict(
	        chi1=dict(
	            ARG=["N", "CA", "CB", "CG"],
	            ASN=["N", "CA", "CB", "CG"],
	            ASP=["N", "CA", "CB", "CG"],
	            CYS=["N", "CA", "CB", "SG"],
	            GLN=["N", "CA", "CB", "CG"],
	            GLU=["N", "CA", "CB", "CG"],
	            HIS=["N", "CA", "CB", "CG"],
	            ILE=["N", "CA", "CB", "CG1"],
	            LEU=["N", "CA", "CB", "CG"],
	            LYS=["N", "CA", "CB", "CG"],
	            MET=["N", "CA", "CB", "CG"],
	            PHE=["N", "CA", "CB", "CG"],
	            PRO=["N", "CA", "CB", "CG"],
	            SER=["N", "CA", "CB", "OG"],
	            THR=["N", "CA", "CB", "OG1"],
	            TRP=["N", "CA", "CB", "CG"],
	            TYR=["N", "CA", "CB", "CG"],
	            VAL=["N", "CA", "CB", "CG1"],
	        ),
	        altchi1=dict(
	            VAL=["N", "CA", "CB", "CG2"],
	        ),
	        chi2=dict(
	            ARG=["CA", "CB", "CG", "CD"],
	            ASN=["CA", "CB", "CG", "OD1"],
	            ASP=["CA", "CB", "CG", "OD1"],
	            GLN=["CA", "CB", "CG", "CD"],
	            GLU=["CA", "CB", "CG", "CD"],
	            HIS=["CA", "CB", "CG", "ND1"],
	            ILE=["CA", "CB", "CG1", "CD1"],
	            LEU=["CA", "CB", "CG", "CD1"],
	            LYS=["CA", "CB", "CG", "CD"],
	            MET=["CA", "CB", "CG", "SD"],
	            PHE=["CA", "CB", "CG", "CD1"],
	            PRO=["CA", "CB", "CG", "CD"],
	            TRP=["CA", "CB", "CG", "CD1"],
	            TYR=["CA", "CB", "CG", "CD1"],
	        ),
	        altchi2=dict(
	            ASP=["CA", "CB", "CG", "OD2"],
	            LEU=["CA", "CB", "CG", "CD2"],
	            PHE=["CA", "CB", "CG", "CD2"],
	            TYR=["CA", "CB", "CG", "CD2"],
	        ),
	        chi3=dict(
	            ARG=["CB", "CG", "CD", "NE"],
	            GLN=["CB", "CG", "CD", "OE1"],
	            GLU=["CB", "CG", "CD", "OE1"],
	            LYS=["CB", "CG", "CD", "CE"],
	            MET=["CB", "CG", "SD", "CE"],
	        ),
	        chi4=dict(
	            ARG=["CG", "CD", "NE", "CZ"],
	            LYS=["CG", "CD", "CE", "NZ"],
	        ),
	        chi5=dict(
	            ARG=["CD", "NE", "CZ", "NH1"],
	        ),
	    )*/
}
