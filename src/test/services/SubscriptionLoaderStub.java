//package services;
//
//import dataBase.Loaders.ISubscriptionLoader;
//import domain.Subscriptions.AssociationMember;
//import domain.Subscriptions.Subscription;
//
//public class SubscriptionLoaderStub implements ISubscriptionLoader {
//
//
//    public Boolean subscriptionFlag;
//
//    public SubscriptionLoaderStub() {
//        subscriptionFlag = true;
//    }
//
//    public void setSubscriptionFlag(Boolean subscriptionFlag) {
//        this.subscriptionFlag = subscriptionFlag;
//    }
//
//
//    public SubscriptionLoaderStub getInstance() {
//
//        return new SubscriptionLoaderStub();
//
//    }
//
//    @Override
//    public Subscription authenticate(String userName, String hashedPassword) {
//        if (this.subscriptionFlag){
//            return new AssociationMember("10","Deni Markovic");
//        }
//        else
//            return null;
//    }
//
//    @Override
//    public boolean isUserExists(String userName) {
//        return false;
//    }
//}
