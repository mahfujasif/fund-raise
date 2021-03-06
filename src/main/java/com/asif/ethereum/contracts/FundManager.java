package com.asif.ethereum.contracts;

import java.io.UnsupportedEncodingException;
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
import org.web3j.tuples.generated.Tuple9;
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
public class FundManager extends Contract {

  private static final String BINARY = "60008054600160a060020a0319163317815560a060405260808181526200003091906401000000006200008b810204565b60005460408051808201909152600581527f6f776e657200000000000000000000000000000000000000000000000000000060208201526200008591600160a060020a0316906401000000006200008b810204565b6200046f565b6000620000976200026c565b600054600160a060020a03163314620000af57600080fd5b600160a060020a0384166000908152600360205260409020549150811515620001035760018054600160a060020a03861660009081526003602052604090208190559062000100908281016200029e565b91505b600160a060020a03841681524260208201526040810183905260018054829190849081106200012e57fe5b60009182526020918290208351600592909202018054600160a060020a031916600160a060020a039092169190911781558282015160018201556040830151805191926200018592600285019290910190620002d2565b5060608201518051620001a391600384019160209091019062000357565b5050604051600160a060020a038616915083907f79b4ad9e4088d716b1c5e624d33e9987582a948412f5b26e8fde2f455f6762a490600090a38060200151836040518082805190602001908083835b60208310620002135780518252601f199092019160209182019101620001f2565b5181516020939093036101000a60001901801990911692169190911790526040519201829003822093507fb144ad1e416436d6e17226d32408d922ce2a52bc8bcc378d682ff31be2178f2c92506000919050a350505050565b6080604051908101604052806000600160a060020a031681526020016000815260200160608152602001606081525090565b815481835581811115620002cd57600502816005028360005260206000209182019101620002cd919062000394565b505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200031557805160ff191683800117855562000345565b8280016001018555821562000345579182015b828111156200034557825182559160200191906001019062000328565b5062000353929150620003e7565b5090565b8280548282559060005260206000209081019282156200034557916020028201828111156200034557825182559160200191906001019062000328565b620003e491905b8082111562000353578054600160a060020a0319168155600060018201819055620003ca600283018262000404565b620003da6003830160006200044f565b506005016200039b565b90565b620003e491905b80821115620003535760008155600101620003ee565b50805460018160011615610100020316600290046000825580601f106200042c57506200044c565b601f0160209004906000526020600020908101906200044c9190620003e7565b50565b50805460008255906000526020600020908101906200044c9190620003e7565b6119fb806200047f6000396000f30060806040526004361061011c5763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630b791430811461011e57806316677878146102a85780632add0946146102d95780632ee07c001461030d57806335e8765d146103395780633a7eca40146103ef5780634816963e1461040a5780634c50a0891461041f57806353b7f6ed1461043a5780635ac453461461045b5780635daf08ca146104765780635e3bad0b1461052657806376e925591461053e5780638b7afe2e146105535780638da5cb5b14610568578063a1a74aae1461057d578063af191a0c14610595578063ba870686146105b3578063bac5ece5146105c8578063c127c247146105e0578063ed88c68e14610647575b005b34801561012a57600080fd5b5061013660043561064f565b604051808a81526020018060200180602001898152602001888152602001878152602001868152602001806020018515151515815260200184810384528c818151815260200191508051906020019080838360005b838110156101a357818101518382015260200161018b565b50505050905090810190601f1680156101d05780820380516001836020036101000a031916815260200191505b5084810383528b5181528b516020918201918d019080838360005b838110156102035781810151838201526020016101eb565b50505050905090810190601f1680156102305780820380516001836020036101000a031916815260200191505b50848103825286518152865160209182019188019080838360005b8381101561026357818101518382015260200161024b565b50505050905090810190601f1680156102905780820380516001836020036101000a031916815260200191505b509c5050505050505050505050505060405180910390f35b3480156102b457600080fd5b506102c060043561086a565b6040805192835260208301919091528051918290030190f35b3480156102e557600080fd5b506102f16004356108fb565b60408051600160a060020a039092168252519081900360200190f35b34801561031957600080fd5b5061032560043561092c565b604080519115158252519081900360200190f35b34801561034557600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526103dd94369492936024939284019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375094975050843595505050602083013592604001359150610d3b9050565b60408051918252519081900360200190f35b3480156103fb57600080fd5b506102c0600435602435610ec9565b34801561041657600080fd5b506103dd610f5f565b34801561042b57600080fd5b506102c0600435602435610faa565b34801561044657600080fd5b506103dd600160a060020a0360043516611039565b34801561046757600080fd5b5061011c60043560243561104b565b34801561048257600080fd5b5061048e6004356111d7565b6040518084600160a060020a0316600160a060020a0316815260200183815260200180602001828103825283818151815260200191508051906020019080838360005b838110156104e95781810151838201526020016104d1565b50505050905090810190601f1680156105165780820380516001836020036101000a031916815260200191505b5094505050505060405180910390f35b34801561053257600080fd5b506103dd6004356112a0565b34801561054a57600080fd5b506103dd6112e6565b34801561055f57600080fd5b506103dd611309565b34801561057457600080fd5b506102f161130e565b34801561058957600080fd5b5061011c60043561131d565b3480156105a157600080fd5b5061011c600435602435604435611383565b3480156105bf57600080fd5b506103dd611433565b3480156105d457600080fd5b506103dd600435611455565b3480156105ec57600080fd5b5060408051602060046024803582810135601f810185900485028601850190965285855261011c958335600160a060020a03169536956044949193909101919081908401838280828437509497506114989650505050505050565b61011c611679565b600280548290811061065d57fe5b90600052602060002090600a0201600091509050806000015490806001018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561070f5780601f106106e45761010080835404028352916020019161070f565b820191906000526020600020905b8154815290600101906020018083116106f257829003601f168201915b50505060028085018054604080516020601f60001961010060018716150201909416959095049283018590048502810185019091528181529596959450909250908301828280156107a15780601f10610776576101008083540402835291602001916107a1565b820191906000526020600020905b81548152906001019060200180831161078457829003601f168201915b505050505090806003015490806004015490806005015490806006015490806008018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156108575780601f1061082c57610100808354040283529160200191610857565b820191906000526020600020905b81548152906001019060200180831161083a57829003601f168201915b5050506009909301549192505060ff1689565b3360009081526003602052604081205481908190151561088957600080fd5b60028054600019810190811061089b57fe5b90600052602060002090600a0201905080600701848154811015156108bc57fe5b906000526020600020906002020160000154925080600701848154811015156108e157fe5b906000526020600020906002020160010154915050915091565b600060018281548110151561090c57fe5b6000918252602090912060059091020154600160a060020a031692915050565b3360009081526003602052604081205481908190151561094b57600080fd5b600060028581548110151561095c57fe5b90600052602060002090600a020160090160006101000a81548160ff0219169083151502179055156109915760009250610d34565b60006002858154811015156109a257fe5b60009182526020909120600a90910201600901805460ff191691151591909117905560028054859081106109d257fe5b90600052602060002090600a0201600601546002858154811015156109f357fe5b90600052602060002090600a020160040154101515610b715760008054600160a060020a031681526003602052604090205460028054610a5192919087908110610a3957fe5b90600052602060002090600a02016004015486611383565b60408051808201909152600681527f636c6f736564000000000000000000000000000000000000000000000000000060208201526002805486908110610a9357fe5b90600052602060002090600a02016008019080519060200190610ab79291906116a8565b506002805485908110610ac657fe5b90600052602060002090600a02016008016040518082805460018160011615610100020316600290048015610b325780601f10610b10576101008083540402835291820191610b32565b820191906000526020600020905b815481529060010190602001808311610b1e575b505060405190819003812092508691507fbf602eb7f71bd73ae0353dc38d7d0ca6a14c7c84a8c3169689a6337e2092f08990600090a360019250610d34565b610b7a84611455565b9150600090505b81811015610c1857610c10600285815481101515610b9b57fe5b90600052602060002090600a020160070182815481101515610bb957fe5b906000526020600020906002020160000154600286815481101515610bda57fe5b90600052602060002090600a020160070183815481101515610bf857fe5b90600052602060002090600202016001015486611383565b600101610b81565b60408051808201909152600881527f63616e63656c656400000000000000000000000000000000000000000000000060208201526002805486908110610c5a57fe5b90600052602060002090600a02016008019080519060200190610c7e9291906116a8565b506002805485908110610c8d57fe5b90600052602060002090600a02016008016040518082805460018160011615610100020316600290048015610cf95780601f10610cd7576101008083540402835291820191610cf9565b820191906000526020600020905b815481529060010190602001808311610ce5575b505060405190819003812092508691507fbf602eb7f71bd73ae0353dc38d7d0ca6a14c7c84a8c3169689a6337e2092f08990600090a3600192505b5050919050565b33600090815260036020526040812054819081901515610d5a57600080fd5b6002805490610d6c9060018301611722565b9150600282815481101515610d7d57fe5b6000918252602080832033845260038252604090932054600a9092029092019081558951909250610db6916001840191908b01906116a8565b508651610dcc90600283019060208a01906116a8565b5060038101869055600581018590556006808201859055604080518082019091528181527f6f6e6c696e6500000000000000000000000000000000000000000000000000006020909101908152610e28916008840191906116a8565b5060098101805460ff191660011790556040518851899190819060208401908083835b60208310610e6a5780518252601f199092019160209182019101610e4b565b5181516020939093036101000a60001901801990911692169190911790526040519201829003822093508592507f38615dbb487d89a7b01481c49ccd20ceda2a1d7f5c15f9abe1a931a10d72e5589160009150a3509695505050505050565b33600090815260036020526040812054819081901515610ee857600080fd5b6001805486908110610ef657fe5b906000526020600020906005020190508060030184815481101515610f1757fe5b906000526020600020015492508060040160008260030186815481101515610f3b57fe5b90600052602060002001548152602001908152602001600020549150509250929050565b3360009081526003602052604081205481901515610f7c57600080fd5b600280546000198101908110610f8e57fe5b90600052602060002090600a02019050806004015491505b5090565b33600090815260036020526040812054819081901515610fc957600080fd5b6002805486908110610fd757fe5b90600052602060002090600a020190508060070184815481101515610ff857fe5b9060005260206000209060020201600001549250806007018481548110151561101d57fe5b9060005260206000209060020201600101549150509250929050565b60036020526000908152604090205481565b600080600080600080600280549050881115611066576111cd565b600280548990811061107457fe5b60009182526020909120600a90910201600981015490965060ff16151561109a576111cd565b33600081815260036020526040902054909550935084318711806110c15750856005015487105b156110cb576111cd565b60048601805488019055600786018054906110e99060018301611753565b9250604080519081016040528085815260200188815250866007018481548110151561111157fe5b6000918252602091829020835160029092020190815591015160019182015580548590811061113c57fe5b90600052602060002090600502019150816003018054809190600101611162919061177f565b905087826003018281548110151561117657fe5b60009182526020808320909101929092558981526004840190915260408082208990555188918a91600160a060020a038916917fe6add99d06d9ad7fc456e746aa162eed6211c5002ef2dacdd67b8d2016822fba91a45b5050505050505050565b60018054829081106111e557fe5b6000918252602091829020600591909102018054600180830154600280850180546040805161010096831615969096026000190190911692909204601f8101889004880285018801909252818452600160a060020a03909416965090949192918301828280156112965780601f1061126b57610100808354040283529160200191611296565b820191906000526020600020905b81548152906001019060200180831161127957829003601f168201915b5050505050905083565b33600090815260036020526040812054819015156112bd57600080fd5b60018054849081106112cb57fe5b60009182526020909120600360059092020101549392505050565b33600090815260036020526040812054151561130157600080fd5b506001545b90565b303190565b600054600160a060020a031681565b600054600160a060020a0316331461133457600080fd5b600060018281548110151561134557fe5b60009182526020909120600590910201805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a039290921691909117905550565b33600090815260036020526040812054151561139e57600080fd5b60018054859081106113ac57fe5b60009182526020822060059091020154604051600160a060020a039091169250829185156108fc02918691818181858888f193505050501580156113f4573d6000803e3d6000fd5b50828282600160a060020a03167f60c798060149d78c699c57ac0adf7cfd72710867c3bbead4a714e33e0885affb60405160405180910390a450505050565b33600090815260036020526040812054151561144e57600080fd5b5060025490565b33600090815260036020526040812054151561147057600080fd5b600280548390811061147e57fe5b600091825260209091206007600a90920201015492915050565b60006114a26117a3565b600054600160a060020a031633146114b957600080fd5b600160a060020a038416600090815260036020526040902054915081151561150a5760018054600160a060020a038616600090815260036020526040902081905590611507908281016117d5565b91505b600160a060020a038416815242602082015260408101839052600180548291908490811061153457fe5b6000918252602091829020835160059290920201805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03909216919091178155828201516001820155604083015180519192611596926002850192909101906116a8565b50606082015180516115b2916003840191602090910190611801565b5050604051600160a060020a038616915083907f79b4ad9e4088d716b1c5e624d33e9987582a948412f5b26e8fde2f455f6762a490600090a38060200151836040518082805190602001908083835b602083106116205780518252601f199092019160209182019101611601565b5181516020939093036101000a60001901801990911692169190911790526040519201829003822093507fb144ad1e416436d6e17226d32408d922ce2a52bc8bcc378d682ff31be2178f2c92506000919050a350505050565b60405130903480156108fc02916000818181858888f193505050501580156116a5573d6000803e3d6000fd5b50565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106116e957805160ff1916838001178555611716565b82800160010185558215611716579182015b828111156117165782518255916020019190600101906116fb565b50610fa692915061183b565b81548183558181111561174e57600a0281600a02836000526020600020918201910161174e9190611855565b505050565b81548183558181111561174e5760020281600202836000526020600020918201910161174e91906118d6565b81548183558181111561174e5760008381526020902061174e91810190830161183b565b6080604051908101604052806000600160a060020a031681526020016000815260200160608152602001606081525090565b81548183558181111561174e5760050281600502836000526020600020918201910161174e91906118f6565b82805482825590600052602060002090810192821561171657916020028201828111156117165782518255916020019190600101906116fb565b61130691905b80821115610fa65760008155600101611841565b61130691905b80821115610fa6576000808255611875600183018261194c565b61188360028301600061194c565b60038201600090556004820160009055600582016000905560068201600090556007820160006118b39190611990565b6118c160088301600061194c565b5060098101805460ff19169055600a0161185b565b61130691905b80821115610fa657600080825560018201556002016118dc565b61130691905b80821115610fa657805473ffffffffffffffffffffffffffffffffffffffff19168155600060018201819055611935600283018261194c565b6119436003830160006119b1565b506005016118fc565b50805460018160011615610100020316600290046000825580601f1061197257506116a5565b601f0160209004906000526020600020908101906116a5919061183b565b50805460008255600202906000526020600020908101906116a591906118d6565b50805460008255906000526020600020908101906116a5919061183b5600a165627a7a72305820011cc638255d6e2fd79c5a06471e120af527028a4a00c55cd7a813c491886c170029";

  public static final String FUNC_EVENTS = "events";

  public static final String FUNC_QUERYDONATION = "queryDonation";

  public static final String FUNC_QUERYBALANCE = "queryBalance";

  public static final String FUNC_CLOSEEVENT = "closeEvent";

  public static final String FUNC_CREATEVENT = "creatEvent";

  public static final String FUNC_GETMEMBERDONATIONS = "getMemberDonations";

  public static final String FUNC_QUERYFUND = "queryFund";

  public static final String FUNC_GETEVENTDONARS = "getEventDonars";

  public static final String FUNC_ADDRESSMEMBERIDMAP = "addressMemberIdMap";

  public static final String FUNC_UPDATEDONATION = "updateDonation";

  public static final String FUNC_MEMBERS = "members";

  public static final String FUNC_TOTALMEMBERDONATIONS = "totalMemberDonations";

  public static final String FUNC_TOTALMEMBERS = "totalMembers";

  public static final String FUNC_CONTRACTBALANCE = "contractBalance";

  public static final String FUNC_OWNER = "owner";

  public static final String FUNC_REMOVEMEMBER = "removeMember";

  public static final String FUNC_REFUND = "refund";

  public static final String FUNC_TOTALEVENTS = "totalEvents";

  public static final String FUNC_TOTALEVENTDONARS = "totalEventDonars";

  public static final String FUNC_ADDMEMBER = "addMember";

  public static final String FUNC_DONATE = "donate";


  public static final Event MEMBERADDEDIDADDRESS_EVENT = new Event("memberAddedIdAddress",
      Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
      }, new TypeReference<Address>() {
      }),
      Arrays.<TypeReference<?>>asList());
  ;

  public static final Event MEMBERADDEDNAMESINCE_EVENT = new Event("memberAddedNameSince",
      Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
      }, new TypeReference<Uint256>() {
      }),
      Arrays.<TypeReference<?>>asList());
  ;

  public static final Event MEMBERREMOVED_EVENT = new Event("memberRemoved",
      Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
      }),
      Arrays.<TypeReference<?>>asList());
  ;

  public static final Event EVENTCREATED_EVENT = new Event("eventCreated",
      Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
      }, new TypeReference<Utf8String>() {
      }),
      Arrays.<TypeReference<?>>asList());
  ;

  public static final Event EVENTCLOSED_EVENT = new Event("eventClosed",
      Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
      }, new TypeReference<Utf8String>() {
      }),
      Arrays.<TypeReference<?>>asList());
  ;

  public static final Event REFUNDED_EVENT = new Event("refunded",
      Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
      }, new TypeReference<Uint256>() {
      }, new TypeReference<Uint256>() {
      }),
      Arrays.<TypeReference<?>>asList());
  ;

  public static final Event DONATED_EVENT = new Event("donated",
      Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
      }, new TypeReference<Uint256>() {
      }, new TypeReference<Uint256>() {
      }),
      Arrays.<TypeReference<?>>asList());
  ;



  protected FundManager(String contractAddress, Web3j web3j, Credentials credentials,
      BigInteger gasPrice, BigInteger gasLimit) {
    super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
  }

  protected FundManager(String contractAddress, Web3j web3j, TransactionManager transactionManager,
      BigInteger gasPrice, BigInteger gasLimit) {
    super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
  }

  public RemoteCall<Tuple9<BigInteger, String, String, BigInteger, BigInteger, BigInteger, BigInteger, String, Boolean>> events(
      BigInteger param0) {
    final Function function = new Function(FUNC_EVENTS,
        Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)),
        Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
        }, new TypeReference<Utf8String>() {
        }, new TypeReference<Utf8String>() {
        }, new TypeReference<Uint256>() {
        }, new TypeReference<Uint256>() {
        }, new TypeReference<Uint256>() {
        }, new TypeReference<Uint256>() {
        }, new TypeReference<Utf8String>() {
        }, new TypeReference<Bool>() {
        }));
    return new RemoteCall<Tuple9<BigInteger, String, String, BigInteger, BigInteger, BigInteger, BigInteger, String, Boolean>>(
        new Callable<Tuple9<BigInteger, String, String, BigInteger, BigInteger, BigInteger, BigInteger, String, Boolean>>() {
          @Override
          public Tuple9<BigInteger, String, String, BigInteger, BigInteger, BigInteger, BigInteger, String, Boolean> call()
              throws Exception {
            List<Type> results = executeCallMultipleValueReturn(function);
            return new Tuple9<BigInteger, String, String, BigInteger, BigInteger, BigInteger, BigInteger, String, Boolean>(
                (BigInteger) results.get(0).getValue(),
                (String) results.get(1).getValue(),
                (String) results.get(2).getValue(),
                (BigInteger) results.get(3).getValue(),
                (BigInteger) results.get(4).getValue(),
                (BigInteger) results.get(5).getValue(),
                (BigInteger) results.get(6).getValue(),
                (String) results.get(7).getValue(),
                (Boolean) results.get(8).getValue());
          }
        });
  }

  public RemoteCall<Tuple2<BigInteger, BigInteger>> queryDonation(BigInteger index) {
    final Function function = new Function(FUNC_QUERYDONATION,
        Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)),
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

  public RemoteCall<String> queryBalance(BigInteger memberId) {
    final Function function = new Function(FUNC_QUERYBALANCE,
        Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(memberId)),
        Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
        }));
    return executeRemoteCallSingleValueReturn(function, String.class);
  }

  public RemoteCall<TransactionReceipt> closeEvent(BigInteger eventId) {
    final Function function = new Function(
        FUNC_CLOSEEVENT,
        Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(eventId)),
        Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteCall<TransactionReceipt> creatEvent(String _title, String _description,
      BigInteger _time, BigInteger _minimum_amount, BigInteger _target_amount) {
    final Function function = new Function(
        FUNC_CREATEVENT,
        Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_title),
            new org.web3j.abi.datatypes.Utf8String(_description),
            new org.web3j.abi.datatypes.generated.Uint256(_time),
            new org.web3j.abi.datatypes.generated.Uint256(_minimum_amount),
            new org.web3j.abi.datatypes.generated.Uint256(_target_amount)),
        Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteCall<Tuple2<BigInteger, BigInteger>> getMemberDonations(BigInteger memberId,
      BigInteger index) {
    final Function function = new Function(FUNC_GETMEMBERDONATIONS,
        Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(memberId),
            new org.web3j.abi.datatypes.generated.Uint256(index)),
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
        Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(eventId),
            new org.web3j.abi.datatypes.generated.Uint256(index)),
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
        Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)),
        Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
        }));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteCall<TransactionReceipt> updateDonation(BigInteger eventId, BigInteger _amount) {
    final Function function = new Function(
        FUNC_UPDATEDONATION,
        Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(eventId),
            new org.web3j.abi.datatypes.generated.Uint256(_amount)),
        Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteCall<Tuple3<String, BigInteger, String>> members(BigInteger param0) {
    final Function function = new Function(FUNC_MEMBERS,
        Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)),
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
        Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(memberId)),
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

  public RemoteCall<BigInteger> contractBalance() {
    final Function function = new Function(FUNC_CONTRACTBALANCE,
        Arrays.<Type>asList(),
        Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
        }));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteCall<String> owner() {
    final Function function = new Function(FUNC_OWNER,
        Arrays.<Type>asList(),
        Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
        }));
    return executeRemoteCallSingleValueReturn(function, String.class);
  }

  public RemoteCall<TransactionReceipt> removeMember(BigInteger memberId) {
    final Function function = new Function(
        FUNC_REMOVEMEMBER,
        Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(memberId)),
        Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteCall<TransactionReceipt> refund(BigInteger donorId, BigInteger amount,
      BigInteger eventId) {
    final Function function = new Function(
        FUNC_REFUND,
        Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(donorId),
            new org.web3j.abi.datatypes.generated.Uint256(amount),
            new org.web3j.abi.datatypes.generated.Uint256(eventId)),
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
        Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(eventId)),
        Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
        }));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteCall<TransactionReceipt> addMember(String targetMember, String memberName) {
    final Function function = new Function(
        FUNC_ADDMEMBER,
        Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(targetMember),
            new org.web3j.abi.datatypes.Utf8String(memberName)),
        Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteCall<TransactionReceipt> donate(BigInteger weiValue) {
    final Function function = new Function(
        FUNC_DONATE,
        Arrays.<Type>asList(),
        Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function, weiValue);
  }

  public static RemoteCall<FundManager> deploy(Web3j web3j, Credentials credentials,
      BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
    return deployRemoteCall(FundManager.class, web3j, credentials, gasPrice, gasLimit, BINARY, "",
        initialWeiValue);
  }

  public static RemoteCall<FundManager> deploy(Web3j web3j, TransactionManager transactionManager,
      BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
    return deployRemoteCall(FundManager.class, web3j, transactionManager, gasPrice, gasLimit,
        BINARY, "", initialWeiValue);
  }

  public List<MemberAddedIdAddressEventResponse> getMemberAddedIdAddressEvents(
      TransactionReceipt transactionReceipt) {
    List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(
        MEMBERADDEDIDADDRESS_EVENT, transactionReceipt);
    ArrayList<MemberAddedIdAddressEventResponse> responses = new ArrayList<MemberAddedIdAddressEventResponse>(
        valueList.size());
    for (Contract.EventValuesWithLog eventValues : valueList) {
      MemberAddedIdAddressEventResponse typedResponse = new MemberAddedIdAddressEventResponse();
      typedResponse.log = eventValues.getLog();
      typedResponse.memberId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
      typedResponse.memberAddress = (String) eventValues.getIndexedValues().get(1).getValue();
      responses.add(typedResponse);
    }
    return responses;
  }

  public Observable<MemberAddedIdAddressEventResponse> memberAddedIdAddressEventObservable(
      EthFilter filter) {
    return web3j.ethLogObservable(filter).map(new Func1<Log, MemberAddedIdAddressEventResponse>() {
      @Override
      public MemberAddedIdAddressEventResponse call(Log log) {
        Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(
            MEMBERADDEDIDADDRESS_EVENT, log);
        MemberAddedIdAddressEventResponse typedResponse = new MemberAddedIdAddressEventResponse();
        typedResponse.log = log;
        typedResponse.memberId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.memberAddress = (String) eventValues.getIndexedValues().get(1).getValue();
        return typedResponse;
      }
    });
  }

  public Observable<MemberAddedIdAddressEventResponse> memberAddedIdAddressEventObservable(
      DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
    EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
    filter.addSingleTopic(EventEncoder.encode(MEMBERADDEDIDADDRESS_EVENT));
    return memberAddedIdAddressEventObservable(filter);
  }

  public List<MemberAddedNameSinceEventResponse> getMemberAddedNameSinceEvents(
      TransactionReceipt transactionReceipt) {
    List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(
        MEMBERADDEDNAMESINCE_EVENT, transactionReceipt);
    ArrayList<MemberAddedNameSinceEventResponse> responses = new ArrayList<MemberAddedNameSinceEventResponse>(
        valueList.size());
    for (Contract.EventValuesWithLog eventValues : valueList) {
      MemberAddedNameSinceEventResponse typedResponse = new MemberAddedNameSinceEventResponse();
      typedResponse.log = eventValues.getLog();
      typedResponse.name = (byte[]) eventValues.getIndexedValues().get(0).getValue();
      typedResponse.since = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
      responses.add(typedResponse);
    }
    return responses;
  }

  public Observable<MemberAddedNameSinceEventResponse> memberAddedNameSinceEventObservable(
      EthFilter filter) {
    return web3j.ethLogObservable(filter).map(new Func1<Log, MemberAddedNameSinceEventResponse>() {
      @Override
      public MemberAddedNameSinceEventResponse call(Log log) {
        Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(
            MEMBERADDEDNAMESINCE_EVENT, log);
        MemberAddedNameSinceEventResponse typedResponse = new MemberAddedNameSinceEventResponse();
        typedResponse.log = log;
        typedResponse.name = (byte[]) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.since = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
        return typedResponse;
      }
    });
  }

  public Observable<MemberAddedNameSinceEventResponse> memberAddedNameSinceEventObservable(
      DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
    EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
    filter.addSingleTopic(EventEncoder.encode(MEMBERADDEDNAMESINCE_EVENT));
    return memberAddedNameSinceEventObservable(filter);
  }

  public List<MemberRemovedEventResponse> getMemberRemovedEvents(
      TransactionReceipt transactionReceipt) {
    List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(MEMBERREMOVED_EVENT,
        transactionReceipt);
    ArrayList<MemberRemovedEventResponse> responses = new ArrayList<MemberRemovedEventResponse>(
        valueList.size());
    for (Contract.EventValuesWithLog eventValues : valueList) {
      MemberRemovedEventResponse typedResponse = new MemberRemovedEventResponse();
      typedResponse.log = eventValues.getLog();
      typedResponse.memberId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
      responses.add(typedResponse);
    }
    return responses;
  }

  public Observable<MemberRemovedEventResponse> memberRemovedEventObservable(EthFilter filter) {
    return web3j.ethLogObservable(filter).map(new Func1<Log, MemberRemovedEventResponse>() {
      @Override
      public MemberRemovedEventResponse call(Log log) {
        Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(MEMBERREMOVED_EVENT,
            log);
        MemberRemovedEventResponse typedResponse = new MemberRemovedEventResponse();
        typedResponse.log = log;
        typedResponse.memberId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
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
    List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(EVENTCREATED_EVENT,
        transactionReceipt);
    ArrayList<EventCreatedEventResponse> responses = new ArrayList<EventCreatedEventResponse>(
        valueList.size());
    for (Contract.EventValuesWithLog eventValues : valueList) {
      EventCreatedEventResponse typedResponse = new EventCreatedEventResponse();
      typedResponse.log = eventValues.getLog();
      typedResponse.eventId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
      typedResponse.title = (byte[]) eventValues.getIndexedValues().get(1).getValue();

      //TypeReference<Utf8String> utf8StringTypeReference = (TypeReference<Utf8String>)eventValues.getIndexedValues().get(1);
//      Utf8String temp = (Utf8String) eventValues.getIndexedValues().get(1);
//      System.out.println("Title: " + temp.getValue());
      responses.add(typedResponse);
    }
    return responses;
  }

  public Observable<EventCreatedEventResponse> eventCreatedEventObservable(EthFilter filter) {
    return web3j.ethLogObservable(filter).map(new Func1<Log, EventCreatedEventResponse>() {
      @Override
      public EventCreatedEventResponse call(Log log) {
        Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(EVENTCREATED_EVENT,
            log);
        EventCreatedEventResponse typedResponse = new EventCreatedEventResponse();
        typedResponse.log = log;
        typedResponse.eventId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.title = (byte[]) eventValues.getIndexedValues().get(1).getValue();
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
    List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(EVENTCLOSED_EVENT,
        transactionReceipt);
    ArrayList<EventClosedEventResponse> responses = new ArrayList<EventClosedEventResponse>(
        valueList.size());
    for (Contract.EventValuesWithLog eventValues : valueList) {
      EventClosedEventResponse typedResponse = new EventClosedEventResponse();
      typedResponse.log = eventValues.getLog();
      typedResponse.eventId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
      typedResponse.status = (byte[]) eventValues.getIndexedValues().get(1).getValue();

      responses.add(typedResponse);
    }
    return responses;
  }

  public Observable<EventClosedEventResponse> eventClosedEventObservable(EthFilter filter) {
    return web3j.ethLogObservable(filter).map(new Func1<Log, EventClosedEventResponse>() {
      @Override
      public EventClosedEventResponse call(Log log) {
        Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(EVENTCLOSED_EVENT,
            log);
        EventClosedEventResponse typedResponse = new EventClosedEventResponse();
        typedResponse.log = log;
        typedResponse.eventId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.status = (byte[]) eventValues.getIndexedValues().get(1).getValue();
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
    List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(REFUNDED_EVENT,
        transactionReceipt);
    ArrayList<RefundedEventResponse> responses = new ArrayList<RefundedEventResponse>(
        valueList.size());
    for (Contract.EventValuesWithLog eventValues : valueList) {
      RefundedEventResponse typedResponse = new RefundedEventResponse();
      typedResponse.log = eventValues.getLog();
      typedResponse.receiver = (String) eventValues.getIndexedValues().get(0).getValue();
      typedResponse.eventId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
      typedResponse.amount = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
      responses.add(typedResponse);
    }
    return responses;
  }

  public Observable<RefundedEventResponse> refundedEventObservable(EthFilter filter) {
    return web3j.ethLogObservable(filter).map(new Func1<Log, RefundedEventResponse>() {
      @Override
      public RefundedEventResponse call(Log log) {
        Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(REFUNDED_EVENT,
            log);
        RefundedEventResponse typedResponse = new RefundedEventResponse();
        typedResponse.log = log;
        typedResponse.receiver = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.eventId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.amount = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
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
    List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(DONATED_EVENT,
        transactionReceipt);
    ArrayList<DonatedEventResponse> responses = new ArrayList<DonatedEventResponse>(
        valueList.size());
    for (Contract.EventValuesWithLog eventValues : valueList) {
      DonatedEventResponse typedResponse = new DonatedEventResponse();
      typedResponse.log = eventValues.getLog();
      typedResponse.donor = (String) eventValues.getIndexedValues().get(0).getValue();
      typedResponse.eventId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
      typedResponse.amount = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
      responses.add(typedResponse);
    }
    return responses;
  }

  public Observable<DonatedEventResponse> donatedEventObservable(EthFilter filter) {
    return web3j.ethLogObservable(filter).map(new Func1<Log, DonatedEventResponse>() {
      @Override
      public DonatedEventResponse call(Log log) {
        Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(DONATED_EVENT, log);
        DonatedEventResponse typedResponse = new DonatedEventResponse();
        typedResponse.log = log;
        typedResponse.donor = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.eventId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.amount = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
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

  public static FundManager load(String contractAddress, Web3j web3j, Credentials credentials,
      BigInteger gasPrice, BigInteger gasLimit) {
    return new FundManager(contractAddress, web3j, credentials, gasPrice, gasLimit);
  }

  public static FundManager load(String contractAddress, Web3j web3j,
      TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
    return new FundManager(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
  }

  public static class MemberAddedIdAddressEventResponse {

    public Log log;

    public BigInteger memberId;

    public String memberAddress;
  }

  public static class MemberAddedNameSinceEventResponse {

    public Log log;

    public byte[] name;

    public BigInteger since;
  }

  public static class MemberRemovedEventResponse {

    public Log log;

    public BigInteger memberId;
  }

  public static class EventCreatedEventResponse {

    public Log log;

    public BigInteger eventId;

    public byte[] title;
  }

  public static class EventClosedEventResponse {

    public Log log;

    public BigInteger eventId;

    public byte[] status;
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
