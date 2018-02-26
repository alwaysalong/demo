//package com.sh.pri.controller;
//
//import java.util.Map;
//
///**
// * Created by admin on 2018/2/26.
// */
//public class ThreadForSendMsg {
//
//    @SuppressWarnings("unchecked")
//    public void pushMessage(Map<String, Object> paramsMap) throws Exception {
//        if(paramsMap !=null && !paramsMap.isEmpty()){
//            //线程数量
//            int corePoolSize = 3;
//            int maximumPoolSize = 3;
//
//            if (DBConstants.walletConfig != null && DBConstants.walletConfig.size() > 0) {
//                if (DBConstants.walletConfig.containsKey("WITHDRAW_CASH_THREAD_COUNT")) {
//                    TWalletConfig config = DBConstants.walletConfig.get("WITHDRAW_CASH_THREAD_COUNT");
//                    if(config !=null && StringUtils.isNotEmpty(config.getVALUE())){
//                        corePoolSize = Tools.toIntegerZero(config.getVALUE());
//                        maximumPoolSize = corePoolSize;
//                    }
//                }
//            }
//
//            String url = EjbClientJndiConstants.PUSH_USERID_MQTTMSG_URL;
//
//            //推送参数设置
//            Map<String,String> httpReqParam = new HashMap<String, String>();
//            httpReqParam.put("appkey",EjbClientJndiConstants.PUSH_MSG_APPKEY);
//            httpReqParam.put("version_no",EjbClientJndiConstants.PUSH_MSG_VERSION_NO);
//            httpReqParam.put("bizName",EjbClientJndiConstants.PUSH_BIZNAME);
//            httpReqParam.put("pushmsgType",EjbClientJndiConstants.PUSH_PUSHMSGTYPE);
//            httpReqParam.put("msgType",EjbClientJndiConstants.PUSH_MSGTYPE);
//            httpReqParam.put("title", "提现成功 ");
//
//            log.info("pushMessage method print url:"+url+";httpReqParam:"+httpReqParam.toString());
//
//            List<TWAccountWithdrawCashDto> list = (List<TWAccountWithdrawCashDto>) paramsMap.get("list");
//
//            if(list !=null && list.size() ==1){
//                corePoolSize = 1;
//            }
//
//            //创建线程池
//            testThreadPool threadPool = new testThreadPool(corePoolSize, maximumPoolSize, 30,TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),new ThreadPoolExecutor.CallerRunsPolicy());
//
//            for (TWAccountWithdrawCashDto twAccountWithdrawCashDto : list) {
//                String bankCode= twAccountWithdrawCashDto.getBankCode();
//                if(StringUtils.isNotEmpty(bankCode) && bankCode.length()>4){
//                    String cardNo = bankCode.substring(bankCode.trim().length()-4, bankCode.trim().length());
//                    httpReqParam.put("userID", twAccountWithdrawCashDto.getUserOnlyId());
//                    httpReqParam.put("content", "提现成功！您有一笔到邮储银行（"+cardNo+"）的提现已成功。点击查看详情！ ");
//
//                    AccountWithdrawCashThread thread = new AccountWithdrawCashThread(httpReqParam, url, twAccountWithdrawCashDto);
//                    threadPool.execute(thread);
//                }else{
//                    log.info("pushMessage method 异常卡号("+bankCode+"),userOnlyId:"+twAccountWithdrawCashDto.getUserOnlyId()+",orderId:"+twAccountWithdrawCashDto.getOrderId()+"推送消息失败");
//                    continue;
//                }
//            }
//
//            //待所有线程执行完毕后，关闭线程池
//            threadPool.shutdown();
//        }else{
//            log.info("AccountWithdrawCashServiceImpl.pushMessage print paramsMap:"+paramsMap.toString()+">>>>>>>推送消息参数为空,不执行推送任务!");
//        }
//    }
//
//    class AccountWithdrawCashThread extends Thread{
//        private Map<String, String> httpReqParam = null;
//        private String url = "";
//        private TWAccountWithdrawCashDto twAccountWithdrawCashDto = null;
//
//        public AccountWithdrawCashThread(Map<String, String> httpReqParam,String url,TWAccountWithdrawCashDto dto){
//            this.httpReqParam = httpReqParam;
//            this.url = url;
//            this.twAccountWithdrawCashDto = dto;
//        }
//
//        @Override
//        public void run() {
//            try {
//                //推送消息
//                JSONObject json = HttpsClientUtils.doPost(httpReqParam, url);
//                log.info("AccountWithdrawCashThread push result:"+json.toString());
//                if("0000".equals(json.getString("returnCode"))){
//                    //更新状态(开启新事务)
//                    updatePushFlagStatus();
//                }
//            } catch (Exception e) {
//                log.info("AccountWithdrawCashThread.run error print httpReqParam:"+httpReqParam);
//                log.info(e.getMessage(),e);
//            }
//        }
//}
