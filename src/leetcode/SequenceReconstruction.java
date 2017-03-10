package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.BitSet;

import org.junit.Test;

public class SequenceReconstruction {

  /*
  Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. 
  The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 104. 
  Reconstruction means building a shortest common supersequence of the sequences in seqs 
  (i.e., a shortest sequence so that all sequences in seqs are subsequences of it). 
  Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.
  
  Example 1:  
  Input: org: [1,2,3], seqs: [[1,2],[1,3]]
  Output: false  
  Explanation:
  [1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.
  
  Example 2:  
  Input: org: [1,2,3], seqs: [[1,2]]  
  Output: false  
  Explanation:
  The reconstructed sequence can only be [1,2].
  
  Example 3:  
  Input: org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]  
  Output: true  
  Explanation:
  The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
  
  Example 4:  
  Input: org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]
  Output: true  
  */

  @Test
  public void test() {
    assertTrue(sequenceReconstruction(new int[] { 1, 2, 3 }, new int[][] { { 1, 2 }, { 1, 3 }, { 2, 3 } }));
    assertTrue(sequenceReconstruction(new int[] { 4, 1, 5, 2, 6, 3 }, new int[][] { { 5, 2, 6, 3 }, { 4, 1, 5, 2 } }));
    assertFalse(sequenceReconstruction(new int[] { 1, 2, 3 }, new int[][] { { 1, 2 }, { 1, 3 } }));
    assertFalse(sequenceReconstruction(new int[] { 1, 2, 3 }, new int[][] { { 1, 2 } }));
  }

  public boolean sequenceReconstruction(int[] org, int[][] seqs) {
    if (seqs == null || seqs.length == 0) return false;
    int orgLen = org.length, idx[] = new int[orgLen + 1];
    BitSet bitSet = new BitSet(orgLen - 1);
    for (int i = 0; i < orgLen; idx[org[i]] = i++);
    for (int[] seq : seqs)
      for (int i = 0; i < seq.length; i++)
        if (seq[i] > orgLen || seq[i] < 0 || (i > 0 && idx[seq[i - 1]] >= idx[seq[i]])) return false;
        else if (i > 0 && idx[seq[i - 1]] + 1 == idx[seq[i]]) bitSet.set(idx[seq[i - 1]]);
    return bitSet.nextClearBit(0) >= orgLen - 1;
  }

}
