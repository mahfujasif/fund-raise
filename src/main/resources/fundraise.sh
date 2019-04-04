rm /home/mahfuj/workspace/fundraise/src/main/resources/bytecodes/FundManager.abi /home/mahfuj/workspace/fundraise/src/main/resources/bytecodes/FundManager.bin /home/mahfuj/workspace/fundraise/src/main/java/com/asif/ethereum/module/event/wrappers/FundManager.java

solc /home/mahfuj/workspace/fundraise/src/main/resources/solidity/fundRaise.sol --bin --abi --optimize -o /home/mahfuj/workspace/fundraise/src/main/resources/bytecodes

web3j solidity generate /home/mahfuj/workspace/fundraise/src/main/resources/bytecodes/FundManager.bin /home/mahfuj/workspace/fundraise/src/main/resources/bytecodes/FundManager.abi -o /home/mahfuj/workspace/fundraise/src/main/java -p com.asif.ethereum.module.event.wrappers

