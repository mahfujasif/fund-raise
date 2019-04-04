package com.asif.ethereum.contracts;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple8;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line
 * tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.5.0.
 */
public class FundManagerOld extends Contract {

  private static final String BINARY = "60008054600160a060020a0319163317815560a0604052608081815262000030919064010000000062000036810204565b620003ca565b600080548190600160a060020a031633146200005157600080fd5b600160a060020a0384166000908152600360205260409020549150811515620000a55760018054600160a060020a038616600090815260036020526040902081905590620000a290828101620001f9565b91505b60806040519081016040528085600160a060020a03168152602001428152602001848152602001828054806020026020016040519081016040528092919081815260200182805480156200011957602002820191906000526020600020905b81548152602001906001019080831162000104575b50505050508152506001838154811015156200013157fe5b60009182526020918290208351600592909202018054600160a060020a031916600160a060020a0390921691909117815582820151600182015560408301518051919262000188926002850192909101906200022d565b5060608201518051620001a6916003840191602090910190620002b2565b505060408051600160a060020a03871681526020810185905260018183015290517f3dbc0a6a4a2b70c34402bc9f8a197d29b91c3bca487a2b3969648d7b60ef228692509081900360600190a150505050565b8154818355818111156200022857600502816005028360005260206000209182019101620002289190620002ef565b505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200027057805160ff1916838001178555620002a0565b82800160010185558215620002a0579182015b82811115620002a057825182559160200191906001019062000283565b50620002ae92915062000342565b5090565b828054828255906000526020600020908101928215620002a05791602002820182811115620002a057825182559160200191906001019062000283565b6200033f91905b80821115620002ae578054600160a060020a03191681556000600182018190556200032560028301826200035f565b62000335600383016000620003aa565b50600501620002f6565b90565b6200033f91905b80821115620002ae576000815560010162000349565b50805460018160011615610100020316600290046000825580601f10620003875750620003a7565b601f016020900490600052602060002090810190620003a7919062000342565b50565b5080546000825590600052602060002090810190620003a7919062000342565b6115ea80620003da6000396000f3006080604052600436106101115763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630b79143081146101135780630cdd53f61461023757806316677878146102595780632ee07c001461028a57806335e8765d146102a257806336f40c61146103585780633a7eca401461036d5780634816963e146103885780634c50a0891461039d57806353b7f6ed146103b85780635daf08ca146103d95780635e3bad0b1461048957806376e92559146104a1578063a1a74aae146104b6578063af191a0c146104ce578063ba870686146104ec578063bac5ece514610501578063c127c24714610519578063c9116b6914610580578063f851a44014610595575b005b34801561011f57600080fd5b5061012b6004356105c6565b6040518089815260200180602001806020018881526020018781526020018681526020018581526020018415151515815260200183810383528a818151815260200191508051906020019080838360005b8381101561019457818101518382015260200161017c565b50505050905090810190601f1680156101c15780820380516001836020036101000a031916815260200191505b5083810382528951815289516020918201918b019080838360005b838110156101f45781810151838201526020016101dc565b50505050905090810190601f1680156102215780820380516001836020036101000a031916815260200191505b509a505050505050505050505060405180910390f35b610245600435602435610744565b604080519115158252519081900360200190f35b34801561026557600080fd5b5061027160043561092a565b6040805192835260208301919091528051918290030190f35b34801561029657600080fd5b506102456004356109a4565b3480156102ae57600080fd5b506040805160206004803580820135601f810184900484028501840190955284845261034694369492936024939284019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375094975050843595505050602083013592604001359150610a9e9050565b60408051918252519081900360200190f35b34801561036457600080fd5b50610346610ba5565b34801561037957600080fd5b50610271600435602435610bab565b34801561039457600080fd5b50610346610c28565b3480156103a957600080fd5b50610271600435602435610c5b565b3480156103c457600080fd5b50610346600160a060020a0360043516610cd1565b3480156103e557600080fd5b506103f1600435610ce3565b6040518084600160a060020a0316600160a060020a0316815260200183815260200180602001828103825283818151815260200191508051906020019080838360005b8381101561044c578181015183820152602001610434565b50505050905090810190601f1680156104795780820380516001836020036101000a031916815260200191505b5094505050505060405180910390f35b34801561049557600080fd5b50610346600435610dac565b3480156104ad57600080fd5b50610346610dd9565b3480156104c257600080fd5b50610111600435610ddf565b3480156104da57600080fd5b50610111600435602435604435610f6b565b3480156104f857600080fd5b50610346611014565b34801561050d57600080fd5b5061034660043561101a565b34801561052557600080fd5b5060408051602060046024803582810135601f8101859004850286018501909652858552610111958335600160a060020a03169536956044949193909101919081908401838280828437509497506110479650505050505050565b34801561058c57600080fd5b5061034661120c565b3480156105a157600080fd5b506105aa611211565b60408051600160a060020a039092168252519081900360200190f35b60028054829081106105d457fe5b9060005260206000209060090201600091509050806000015490806001018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106865780601f1061065b57610100808354040283529160200191610686565b820191906000526020600020905b81548152906001019060200180831161066957829003601f168201915b50505060028085018054604080516020601f60001961010060018716150201909416959095049283018590048502810185019091528181529596959450909250908301828280156107185780601f106106ed57610100808354040283529160200191610718565b820191906000526020600020905b8154815290600101906020018083116106fb57829003601f168201915b505050600384015460048501546005860154600687015460089097015495969295919450925060ff1688565b6000806000806000806000806002805490508a101515610767576000975061091d565b600280548b90811061077557fe5b60009182526020909120600990910201600881015490975060ff16151561079f576000975061091d565b33600081815260036020526040902054909650945085318911806107c65750866005015489105b156107d4576000975061091d565b60405130945084908a156108fc02908b906000818181858888f19350505050158015610804573d6000803e3d6000fd5b506004870180548a019055600787018054906108239060018301611220565b925060408051908101604052808681526020018a815250876007018481548110151561084b57fe5b6000918252602091829020835160029092020190815591015160019182015580548690811061087657fe5b9060005260206000209060050201915081600301805480919060010161089c9190611251565b90508982600301828154811015156108b057fe5b60009182526020808320909101929092558b81526004840182526040908190208b90558051600160a060020a03891681529182018c90528181018b9052517fe6add99d06d9ad7fc456e746aa162eed6211c5002ef2dacdd67b8d2016822fba9181900360600190a1600197505b5050505050505092915050565b600280546000918291829190600019810190811061094457fe5b90600052602060002090600902019050806007018481548110151561096557fe5b9060005260206000209060020201600001549250806007018481548110151561098a57fe5b906000526020600020906002020160010154915050915091565b6000806000806002858154811015156109b957fe5b90600052602060002090600902019250826006015483600401541015156109ef5760088301805460ff1916905560019350610a96565b6109f88561101a565b9150600090505b81811015610a5e57610a568360070182815481101515610a1b57fe5b9060005260206000209060020201600001548460070183815481101515610a3e57fe5b90600052602060002090600202016001015487610f6b565b6001016109ff565b6040805186815290517f86d7ce8cea57acc6b1c4bf7a21bf2a3912df0a60072ec4f1323c270720b6f1a99181900360200190a1600193505b505050919050565b600080600060028054809190600101610ab79190611275565b9150600282815481101515610ac857fe5b600091825260208083203384526003825260409093205460099092029092019081558951909250610b01916001840191908b01906112a1565b508651610b1790600283019060208a01906112a1565b5060038101869055600581018590556006810184905560088101805460ff191660011790559091508190610b9a565b83811015610b5e578181015183820152602001610b46565b50505050905090810190601f168015610b8b5780820380516001836020036101000a031916815260200191505b50935050505060405180910390a15b505095945050505050565b30315b90565b6000806000600185815481101515610bbf57fe5b906000526020600020906005020190508060030184815481101515610be057fe5b906000526020600020015492508060040160008260030186815481101515610c0457fe5b90600052602060002001548152602001908152602001600020549150509250929050565b6002805460009182916000198101908110610c3f57fe5b90600052602060002090600902019050806004015491505b5090565b6000806000600285815481101515610c6f57fe5b906000526020600020906009020190508060070184815481101515610c9057fe5b90600052602060002090600202016000015492508060070184815481101515610cb557fe5b9060005260206000209060020201600101549150509250929050565b60036020526000908152604090205481565b6001805482908110610cf157fe5b6000918252602091829020600591909102018054600180830154600280850180546040805161010096831615969096026000190190911692909204601f8101889004880285018801909252818452600160a060020a0390941696509094919291830182828015610da25780601f10610d7757610100808354040283529160200191610da2565b820191906000526020600020905b815481529060010190602001808311610d8557829003601f168201915b5050505050905083565b600080600183815481101515610dbe57fe5b60009182526020909120600360059092020101549392505050565b60015490565b60008054600160a060020a03163314610df757600080fd5b50805b60015460001901811015610ebf5760018054828201908110610e1857fe5b9060005260206000209060050201600182815481101515610e3557fe5b600091825260209091208254600590920201805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a039092169190911781556001808301548183015560028084018054610e9f93838601939082161561010002600019019091160461131b565b5060038281018054610eb49284019190611390565b505050600101610dfa565b600180546000198101908110610ed157fe5b600091825260208220600590910201805473ffffffffffffffffffffffffffffffffffffffff191681556001810182905590610f1060028301826113cf565b610f1e600383016000611416565b50506001805490610f33906000198301611434565b506040805183815290517fafeaec77175a1d381875107147d96209fffeb9ac2844587cad186062c7dc18839181900360200190a15050565b6000600184815481101515610f7c57fe5b60009182526020822060059091020154604051600160a060020a039091169250829185156108fc02918691818181858888f19350505050158015610fc4573d6000803e3d6000fd5b5060408051600160a060020a03831681526020810184905280820185905290517f60c798060149d78c699c57ac0adf7cfd72710867c3bbead4a714e33e0885affb9181900360600190a150505050565b60025490565b60008060028381548110151561102c57fe5b60009182526020909120600760099092020101549392505050565b600080548190600160a060020a0316331461106157600080fd5b600160a060020a03841660009081526003602052604090205491508115156110b25760018054600160a060020a0386166000908152600360205260409020819055906110af90828101611434565b91505b60806040519081016040528085600160a060020a031681526020014281526020018481526020018280548060200260200160405190810160405280929190818152602001828054801561112457602002820191906000526020600020905b815481526020019060010190808311611110575b505050505081525060018381548110151561113b57fe5b6000918252602091829020835160059290920201805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0390921691909117815582820151600182015560408301518051919261119d926002850192909101906112a1565b50606082015180516111b9916003840191602090910190611460565b505060408051600160a060020a03871681526020810185905260018183015290517f3dbc0a6a4a2b70c34402bc9f8a197d29b91c3bca487a2b3969648d7b60ef228692509081900360600190a150505050565b333190565b600054600160a060020a031681565b81548183558181111561124c5760020281600202836000526020600020918201910161124c919061149a565b505050565b81548183558181111561124c5760008381526020902061124c9181019083016114ba565b81548183558181111561124c5760090281600902836000526020600020918201910161124c91906114d4565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106112e257805160ff191683800117855561130f565b8280016001018555821561130f579182015b8281111561130f5782518255916020019190600101906112f4565b50610c579291506114ba565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10611354578054855561130f565b8280016001018555821561130f57600052602060002091601f016020900482015b8281111561130f578254825591600101919060010190611375565b82805482825590600052602060002090810192821561130f5760005260206000209182018281111561130f578254825591600101919060010190611375565b50805460018160011615610100020316600290046000825580601f106113f55750611413565b601f01602090049060005260206000209081019061141391906114ba565b50565b508054600082559060005260206000209081019061141391906114ba565b81548183558181111561124c5760050281600502836000526020600020918201910161124c9190611547565b82805482825590600052602060002090810192821561130f579160200282018281111561130f5782518255916020019190600101906112f4565b610ba891905b80821115610c5757600080825560018201556002016114a0565b610ba891905b80821115610c5757600081556001016114c0565b610ba891905b80821115610c575760008082556114f460018301826113cf565b6115026002830160006113cf565b6003820160009055600482016000905560058201600090556006820160009055600782016000611532919061159d565b5060088101805460ff191690556009016114da565b610ba891905b80821115610c5757805473ffffffffffffffffffffffffffffffffffffffff1916815560006001820181905561158660028301826113cf565b611594600383016000611416565b5060050161154d565b5080546000825560020290600052602060002090810190611413919061149a5600a165627a7a723058207a77718a21d4fab7e7a54c7845e93ed5329bc8b1d7eb7222531554beba09bb040029";

  public static final String FUNC_EVENTS = "events";

  public static final String FUNC_DONATE = "donate";

  public static final String FUNC_QUERYDONATION = "queryDonation";

  public static final String FUNC_CLOSEEVENT = "closeEvent";

  public static final String FUNC_CREATEVENT = "creatEvent";

  public static final String FUNC_QUERYBALANCE = "queryBalance";

  public static final String FUNC_GETMEMBERDONATIONS = "getMemberDonations";

  public static final String FUNC_QUERYFUND = "queryFund";

  public static final String FUNC_GETEVENTDONARS = "getEventDonars";

  public static final String FUNC_ADDRESSMEMBERIDMAP = "addressMemberIdMap";

  public static final String FUNC_MEMBERS = "members";

  public static final String FUNC_TOTALMEMBERDONATIONS = "totalMemberDonations";

  public static final String FUNC_TOTALMEMBERS = "totalMembers";

  public static final String FUNC_REMOVEMEMBER = "removeMember";

  public static final String FUNC_REFUND = "refund";

  public static final String FUNC_TOTALEVENTS = "totalEvents";

  public static final String FUNC_TOTALEVENTDONARS = "totalEventDonars";

  public static final String FUNC_ADDMEMBER = "addMember";

  public static final String FUNC_MYBALANCE = "myBalance";

  public static final String FUNC_ADMIN = "admin";

  public static final Event MEMBERADDED_EVENT = new Event("memberAdded",
      Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
      }, new TypeReference<Uint256>() {
      }, new TypeReference<Bool>() {
      }), Arrays.<TypeReference<?>>asList());
  ;

  public static final Event MEMBERREMOVED_EVENT = new Event("memberRemoved",
      Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
      }), Arrays.<TypeReference<?>>asList());
  ;

  public static final Event EVENTCREATED_EVENT = new Event("eventCreated",
      Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
      }, new TypeReference<Utf8String>() {
      }),
      Arrays.<TypeReference<?>>asList());
  ;

  public static final Event EVENTCLOSED_EVENT = new Event("eventClosed",
      Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
      }), Arrays.<TypeReference<?>>asList());
  ;

  public static final Event REFUNDED_EVENT = new Event("refunded",
      Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
      }, new TypeReference<Uint256>() {
      }, new TypeReference<Uint256>() {
      }), Arrays.<TypeReference<?>>asList());
  ;

  public static final Event DONATED_EVENT = new Event("donated",
      Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
      }, new TypeReference<Uint256>() {
      }, new TypeReference<Uint256>() {
      }), Arrays.<TypeReference<?>>asList());
  ;

  protected FundManagerOld(String contractAddress, Web3j web3j, Credentials credentials,
      BigInteger gasPrice, BigInteger gasLimit) {
    super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
  }

  protected FundManagerOld(String contractAddress, Web3j web3j,
      TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
    super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
  }

  public RemoteCall<Tuple8<BigInteger, String, String, BigInteger, BigInteger, BigInteger, BigInteger, Boolean>> events(
      BigInteger param0) {
    final Function function = new Function(FUNC_EVENTS,
        Arrays.<Type>asList(new Uint256(param0)),
        Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
        }, new TypeReference<Utf8String>() {
        }, new TypeReference<Utf8String>() {
        }, new TypeReference<Uint256>() {
        }, new TypeReference<Uint256>() {
        }, new TypeReference<Uint256>() {
        }, new TypeReference<Uint256>() {
        }, new TypeReference<Bool>() {
        }));
    return new RemoteCall<Tuple8<BigInteger, String, String, BigInteger, BigInteger, BigInteger, BigInteger, Boolean>>(
        new Callable<Tuple8<BigInteger, String, String, BigInteger, BigInteger, BigInteger, BigInteger, Boolean>>() {
          @Override
          public Tuple8<BigInteger, String, String, BigInteger, BigInteger, BigInteger, BigInteger, Boolean> call()
              throws Exception {
            List<Type> results = executeCallMultipleValueReturn(function);
            return new Tuple8<BigInteger, String, String, BigInteger, BigInteger, BigInteger, BigInteger, Boolean>(
                (BigInteger) results.get(0).getValue(),
                (String) results.get(1).getValue(),
                (String) results.get(2).getValue(),
                (BigInteger) results.get(3).getValue(),
                (BigInteger) results.get(4).getValue(),
                (BigInteger) results.get(5).getValue(),
                (BigInteger) results.get(6).getValue(),
                (Boolean) results.get(7).getValue());
          }
        });
  }

  public RemoteCall<TransactionReceipt> donate(BigInteger eventId, BigInteger _amount,
      BigInteger weiValue) {
    final Function function = new Function(
        FUNC_DONATE,
        Arrays.<Type>asList(new Uint256(eventId),
            new Uint256(_amount)),
        Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function, weiValue);
  }

  public RemoteCall<Tuple2<BigInteger, BigInteger>> queryDonation(BigInteger index) {
    final Function function = new Function(FUNC_QUERYDONATION,
        Arrays.<Type>asList(new Uint256(index)),
        Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
        }, new TypeReference<Uint256>() {
        }));
    return new RemoteCall<Tuple2<BigInteger, BigInteger>>(
        new Callable<Tuple2<BigInteger, BigInteger>>() {
          @Override
          public Tuple2<BigInteger, BigInteger> call() throws Exception {
            List<Type> results = executeCallMultipleValueReturn(function);
            return new Tuple2<BigInteger, BigInteger>(
                (BigInteger) results.get(0).getValue(),
                (BigInteger) results.get(1).getValue());
          }
        });
  }

  public RemoteCall<TransactionReceipt> closeEvent(BigInteger eventId) {
    final Function function = new Function(
        FUNC_CLOSEEVENT,
        Arrays.<Type>asList(new Uint256(eventId)),
        Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteCall<TransactionReceipt> creatEvent(String _title, String _description,
      BigInteger _time, BigInteger _minimum_amount, BigInteger _target_amount) {
    final Function function = new Function(
        FUNC_CREATEVENT,
        Arrays.<Type>asList(new Utf8String(_title),
            new Utf8String(_description),
            new Uint256(_time),
            new Uint256(_minimum_amount),
            new Uint256(_target_amount)),
        Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteCall<BigInteger> queryBalance() {
    final Function function = new Function(FUNC_QUERYBALANCE,
        Arrays.<Type>asList(),
        Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
        }));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteCall<Tuple2<BigInteger, BigInteger>> getMemberDonations(BigInteger memberId,
      BigInteger index) {
    final Function function = new Function(FUNC_GETMEMBERDONATIONS,
        Arrays.<Type>asList(new Uint256(memberId),
            new Uint256(index)),
        Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
        }, new TypeReference<Uint256>() {
        }));
    return new RemoteCall<Tuple2<BigInteger, BigInteger>>(
        new Callable<Tuple2<BigInteger, BigInteger>>() {
          @Override
          public Tuple2<BigInteger, BigInteger> call() throws Exception {
            List<Type> results = executeCallMultipleValueReturn(function);
            return new Tuple2<BigInteger, BigInteger>(
                (BigInteger) results.get(0).getValue(),
                (BigInteger) results.get(1).getValue());
          }
        });
  }

  public RemoteCall<BigInteger> queryFund() {
    final Function function = new Function(FUNC_QUERYFUND,
        Arrays.<Type>asList(),
        Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
        }));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteCall<Tuple2<BigInteger, BigInteger>> getEventDonars(BigInteger eventId,
      BigInteger index) {
    final Function function = new Function(FUNC_GETEVENTDONARS,
        Arrays.<Type>asList(new Uint256(eventId),
            new Uint256(index)),
        Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
        }, new TypeReference<Uint256>() {
        }));
    return new RemoteCall<Tuple2<BigInteger, BigInteger>>(
        new Callable<Tuple2<BigInteger, BigInteger>>() {
          @Override
          public Tuple2<BigInteger, BigInteger> call() throws Exception {
            List<Type> results = executeCallMultipleValueReturn(function);
            return new Tuple2<BigInteger, BigInteger>(
                (BigInteger) results.get(0).getValue(),
                (BigInteger) results.get(1).getValue());
          }
        });
  }

  public RemoteCall<BigInteger> addressMemberIdMap(String param0) {
    final Function function = new Function(FUNC_ADDRESSMEMBERIDMAP,
        Arrays.<Type>asList(new Address(param0)),
        Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
        }));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteCall<Tuple3<String, BigInteger, String>> members(BigInteger param0) {
    final Function function = new Function(FUNC_MEMBERS,
        Arrays.<Type>asList(new Uint256(param0)),
        Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
        }, new TypeReference<Uint256>() {
        }, new TypeReference<Utf8String>() {
        }));
    return new RemoteCall<Tuple3<String, BigInteger, String>>(
        new Callable<Tuple3<String, BigInteger, String>>() {
          @Override
          public Tuple3<String, BigInteger, String> call() throws Exception {
            List<Type> results = executeCallMultipleValueReturn(function);
            return new Tuple3<String, BigInteger, String>(
                (String) results.get(0).getValue(),
                (BigInteger) results.get(1).getValue(),
                (String) results.get(2).getValue());
          }
        });
  }

  public RemoteCall<BigInteger> totalMemberDonations(BigInteger memberId) {
    final Function function = new Function(FUNC_TOTALMEMBERDONATIONS,
        Arrays.<Type>asList(new Uint256(memberId)),
        Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
        }));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteCall<BigInteger> totalMembers() {
    final Function function = new Function(FUNC_TOTALMEMBERS,
        Arrays.<Type>asList(),
        Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
        }));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteCall<TransactionReceipt> removeMember(BigInteger memberId) {
    final Function function = new Function(
        FUNC_REMOVEMEMBER,
        Arrays.<Type>asList(new Uint256(memberId)),
        Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteCall<TransactionReceipt> refund(BigInteger donorId, BigInteger amount,
      BigInteger eventId) {
    final Function function = new Function(
        FUNC_REFUND,
        Arrays.<Type>asList(new Uint256(donorId),
            new Uint256(amount),
            new Uint256(eventId)),
        Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteCall<BigInteger> totalEvents() {
    final Function function = new Function(FUNC_TOTALEVENTS,
        Arrays.<Type>asList(),
        Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
        }));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteCall<BigInteger> totalEventDonars(BigInteger eventId) {
    final Function function = new Function(FUNC_TOTALEVENTDONARS,
        Arrays.<Type>asList(new Uint256(eventId)),
        Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
        }));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteCall<TransactionReceipt> addMember(String targetMember, String memberName) {
    final Function function = new Function(
        FUNC_ADDMEMBER,
        Arrays.<Type>asList(new Address(targetMember),
            new Utf8String(memberName)),
        Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteCall<BigInteger> myBalance() {
    final Function function = new Function(FUNC_MYBALANCE,
        Arrays.<Type>asList(),
        Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
        }));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteCall<String> admin() {
    final Function function = new Function(FUNC_ADMIN,
        Arrays.<Type>asList(),
        Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
        }));
    return executeRemoteCallSingleValueReturn(function, String.class);
  }

  public static RemoteCall<FundManagerOld> deploy(Web3j web3j, Credentials credentials,
      BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
    return deployRemoteCall(FundManagerOld.class, web3j, credentials, gasPrice, gasLimit, BINARY,
        "", initialWeiValue);
  }

  public static RemoteCall<FundManagerOld> deploy(Web3j web3j,
      TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit,
      BigInteger initialWeiValue) {
    return deployRemoteCall(FundManagerOld.class, web3j, transactionManager, gasPrice, gasLimit,
        BINARY, "", initialWeiValue);
  }

  public List<MemberAddedEventResponse> getMemberAddedEvents(
      TransactionReceipt transactionReceipt) {
    List<EventValuesWithLog> valueList = extractEventParametersWithLog(MEMBERADDED_EVENT,
        transactionReceipt);
    ArrayList<MemberAddedEventResponse> responses = new ArrayList<MemberAddedEventResponse>(
        valueList.size());
    for (EventValuesWithLog eventValues : valueList) {
      MemberAddedEventResponse typedResponse = new MemberAddedEventResponse();
      typedResponse.log = eventValues.getLog();
      typedResponse.memberAddress = (String) eventValues.getNonIndexedValues().get(0).getValue();
      typedResponse.memberId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
      typedResponse.isMember = (Boolean) eventValues.getNonIndexedValues().get(2).getValue();
      responses.add(typedResponse);
    }
    return responses;
  }

  public Observable<MemberAddedEventResponse> memberAddedEventObservable(EthFilter filter) {
    return web3j.ethLogObservable(filter).map(new Func1<Log, MemberAddedEventResponse>() {
      @Override
      public MemberAddedEventResponse call(Log log) {
        EventValuesWithLog eventValues = extractEventParametersWithLog(MEMBERADDED_EVENT, log);
        MemberAddedEventResponse typedResponse = new MemberAddedEventResponse();
        typedResponse.log = log;
        typedResponse.memberAddress = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.memberId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.isMember = (Boolean) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
      }
    });
  }

  public Observable<MemberAddedEventResponse> memberAddedEventObservable(
      DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
    EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
    filter.addSingleTopic(EventEncoder.encode(MEMBERADDED_EVENT));
    return memberAddedEventObservable(filter);
  }

  public List<MemberRemovedEventResponse> getMemberRemovedEvents(
      TransactionReceipt transactionReceipt) {
    List<EventValuesWithLog> valueList = extractEventParametersWithLog(MEMBERREMOVED_EVENT,
        transactionReceipt);
    ArrayList<MemberRemovedEventResponse> responses = new ArrayList<MemberRemovedEventResponse>(
        valueList.size());
    for (EventValuesWithLog eventValues : valueList) {
      MemberRemovedEventResponse typedResponse = new MemberRemovedEventResponse();
      typedResponse.log = eventValues.getLog();
      typedResponse.memberId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
      responses.add(typedResponse);
    }
    return responses;
  }

  public Observable<MemberRemovedEventResponse> memberRemovedEventObservable(EthFilter filter) {
    return web3j.ethLogObservable(filter).map(new Func1<Log, MemberRemovedEventResponse>() {
      @Override
      public MemberRemovedEventResponse call(Log log) {
        EventValuesWithLog eventValues = extractEventParametersWithLog(MEMBERREMOVED_EVENT, log);
        MemberRemovedEventResponse typedResponse = new MemberRemovedEventResponse();
        typedResponse.log = log;
        typedResponse.memberId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
      }
    });
  }

  public Observable<MemberRemovedEventResponse> memberRemovedEventObservable(
      DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
    EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
    filter.addSingleTopic(EventEncoder.encode(MEMBERREMOVED_EVENT));
    return memberRemovedEventObservable(filter);
  }

  public List<EventCreatedEventResponse> getEventCreatedEvents(
      TransactionReceipt transactionReceipt) {
    List<EventValuesWithLog> valueList = extractEventParametersWithLog(EVENTCREATED_EVENT,
        transactionReceipt);
    ArrayList<EventCreatedEventResponse> responses = new ArrayList<EventCreatedEventResponse>(
        valueList.size());
    for (EventValuesWithLog eventValues : valueList) {
      EventCreatedEventResponse typedResponse = new EventCreatedEventResponse();
      typedResponse.log = eventValues.getLog();
      typedResponse.eventId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
      typedResponse.title = (String) eventValues.getNonIndexedValues().get(1).getValue();
      responses.add(typedResponse);
    }
    return responses;
  }

  public Observable<EventCreatedEventResponse> eventCreatedEventObservable(EthFilter filter) {
    return web3j.ethLogObservable(filter).map(new Func1<Log, EventCreatedEventResponse>() {
      @Override
      public EventCreatedEventResponse call(Log log) {
        EventValuesWithLog eventValues = extractEventParametersWithLog(EVENTCREATED_EVENT, log);
        EventCreatedEventResponse typedResponse = new EventCreatedEventResponse();
        typedResponse.log = log;
        typedResponse.eventId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.title = (String) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
      }
    });
  }

  public Observable<EventCreatedEventResponse> eventCreatedEventObservable(
      DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
    EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
    filter.addSingleTopic(EventEncoder.encode(EVENTCREATED_EVENT));
    return eventCreatedEventObservable(filter);
  }

  public List<EventClosedEventResponse> getEventClosedEvents(
      TransactionReceipt transactionReceipt) {
    List<EventValuesWithLog> valueList = extractEventParametersWithLog(EVENTCLOSED_EVENT,
        transactionReceipt);
    ArrayList<EventClosedEventResponse> responses = new ArrayList<EventClosedEventResponse>(
        valueList.size());
    for (EventValuesWithLog eventValues : valueList) {
      EventClosedEventResponse typedResponse = new EventClosedEventResponse();
      typedResponse.log = eventValues.getLog();
      typedResponse.eventId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
      responses.add(typedResponse);
    }
    return responses;
  }

  public Observable<EventClosedEventResponse> eventClosedEventObservable(EthFilter filter) {
    return web3j.ethLogObservable(filter).map(new Func1<Log, EventClosedEventResponse>() {
      @Override
      public EventClosedEventResponse call(Log log) {
        EventValuesWithLog eventValues = extractEventParametersWithLog(EVENTCLOSED_EVENT, log);
        EventClosedEventResponse typedResponse = new EventClosedEventResponse();
        typedResponse.log = log;
        typedResponse.eventId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
      }
    });
  }

  public Observable<EventClosedEventResponse> eventClosedEventObservable(
      DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
    EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
    filter.addSingleTopic(EventEncoder.encode(EVENTCLOSED_EVENT));
    return eventClosedEventObservable(filter);
  }

  public List<RefundedEventResponse> getRefundedEvents(TransactionReceipt transactionReceipt) {
    List<EventValuesWithLog> valueList = extractEventParametersWithLog(REFUNDED_EVENT,
        transactionReceipt);
    ArrayList<RefundedEventResponse> responses = new ArrayList<RefundedEventResponse>(
        valueList.size());
    for (EventValuesWithLog eventValues : valueList) {
      RefundedEventResponse typedResponse = new RefundedEventResponse();
      typedResponse.log = eventValues.getLog();
      typedResponse.receiver = (String) eventValues.getNonIndexedValues().get(0).getValue();
      typedResponse.eventId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
      typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
      responses.add(typedResponse);
    }
    return responses;
  }

  public Observable<RefundedEventResponse> refundedEventObservable(EthFilter filter) {
    return web3j.ethLogObservable(filter).map(new Func1<Log, RefundedEventResponse>() {
      @Override
      public RefundedEventResponse call(Log log) {
        EventValuesWithLog eventValues = extractEventParametersWithLog(REFUNDED_EVENT, log);
        RefundedEventResponse typedResponse = new RefundedEventResponse();
        typedResponse.log = log;
        typedResponse.receiver = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.eventId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
      }
    });
  }

  public Observable<RefundedEventResponse> refundedEventObservable(DefaultBlockParameter startBlock,
      DefaultBlockParameter endBlock) {
    EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
    filter.addSingleTopic(EventEncoder.encode(REFUNDED_EVENT));
    return refundedEventObservable(filter);
  }

  public List<DonatedEventResponse> getDonatedEvents(TransactionReceipt transactionReceipt) {
    List<EventValuesWithLog> valueList = extractEventParametersWithLog(DONATED_EVENT,
        transactionReceipt);
    ArrayList<DonatedEventResponse> responses = new ArrayList<DonatedEventResponse>(
        valueList.size());
    for (EventValuesWithLog eventValues : valueList) {
      DonatedEventResponse typedResponse = new DonatedEventResponse();
      typedResponse.log = eventValues.getLog();
      typedResponse.donor = (String) eventValues.getNonIndexedValues().get(0).getValue();
      typedResponse.eventId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
      typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
      responses.add(typedResponse);
    }
    return responses;
  }

  public Observable<DonatedEventResponse> donatedEventObservable(EthFilter filter) {
    return web3j.ethLogObservable(filter).map(new Func1<Log, DonatedEventResponse>() {
      @Override
      public DonatedEventResponse call(Log log) {
        EventValuesWithLog eventValues = extractEventParametersWithLog(DONATED_EVENT, log);
        DonatedEventResponse typedResponse = new DonatedEventResponse();
        typedResponse.log = log;
        typedResponse.donor = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.eventId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
      }
    });
  }

  public Observable<DonatedEventResponse> donatedEventObservable(DefaultBlockParameter startBlock,
      DefaultBlockParameter endBlock) {
    EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
    filter.addSingleTopic(EventEncoder.encode(DONATED_EVENT));
    return donatedEventObservable(filter);
  }

  public static FundManagerOld load(String contractAddress, Web3j web3j, Credentials credentials,
      BigInteger gasPrice, BigInteger gasLimit) {
    return new FundManagerOld(contractAddress, web3j, credentials, gasPrice, gasLimit);
  }

  public static FundManagerOld load(String contractAddress, Web3j web3j,
      TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
    return new FundManagerOld(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
  }

  public static class MemberAddedEventResponse {

    public Log log;

    public String memberAddress;

    public BigInteger memberId;

    public Boolean isMember;
  }

  public static class MemberRemovedEventResponse {

    public Log log;

    public BigInteger memberId;
  }

  public static class EventCreatedEventResponse {

    public Log log;

    public BigInteger eventId;

    public String title;
  }

  public static class EventClosedEventResponse {

    public Log log;

    public BigInteger eventId;
  }

  public static class RefundedEventResponse {

    public Log log;

    public String receiver;

    public BigInteger eventId;

    public BigInteger amount;
  }

  public static class DonatedEventResponse {

    public Log log;

    public String donor;

    public BigInteger eventId;

    public BigInteger amount;
  }
}
