package com.asif.ethereum.modules.members.Assemblers;

import com.asif.ethereum.modules.members.resources.DonationResource;
import com.asif.ethereum.modules.members.resources.MemberResource;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.stereotype.Component;
import org.web3j.tuples.generated.Tuple3;

@Component
public class MemberResourceAssembler {
    public MemberResource toResource(BigInteger memberId,
        Tuple3<String, BigInteger, String> memberNonListResources,
        List<DonationResource> donationResources){

      Timestamp since = new Timestamp(memberNonListResources.getValue2().multiply(new BigInteger("1000")).longValue());
      return new MemberResource().setMemberId(memberId)
          .setMemberAddress(memberNonListResources.getValue1())
          .setSince(since.toLocalDateTime())
          .setName(memberNonListResources.getValue3())
          .setDonations(donationResources);
    }

  public MemberResource toResource(BigInteger memberId,
      String address,
      String name,
      BigInteger since){

    Timestamp memberSince = new Timestamp(since.multiply(new BigInteger("1000")).longValue());
    return new MemberResource().setMemberId(memberId)
        .setMemberAddress(address)
        .setSince(memberSince.toLocalDateTime())
        .setName(name)
        .setDonations(null);
  }
}
