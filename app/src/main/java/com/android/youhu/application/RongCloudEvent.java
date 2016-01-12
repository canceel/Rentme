package com.android.youhu.application;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.widget.provider.CameraInputProvider;
import io.rong.imkit.widget.provider.ImageInputProvider;
import io.rong.imkit.widget.provider.InputProvider;
import io.rong.imkit.widget.provider.VoIPInputProvider;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;
import io.rong.message.ImageMessage;

/**
 * Created by zhjchen on 1/29/15.
 */

/**
 * 融云SDK事件监听处理。
 * 把事件统一处理，开发者可直接复制到自己的项目中去使用。
 * <p/>
 * 该类包含的监听事件有：
 * 1、消息接收器：OnReceiveMessageListener。
 * 2、发出消息接收器：OnSendMessageListener。
 * 3、用户信息提供者：GetUserInfoProvider。
 * 4、好友信息提供者：GetFriendsProvider。
 * 5、群组信息提供者：GetGroupInfoProvider。
 * 6、会话界面操作的监听器：ConversationBehaviorListener。
 * 7、连接状态监听器，以获取连接相关状态：ConnectionStatusListener。
 * 8、地理位置提供者：LocationProvider。
 */
public final class RongCloudEvent implements RongIM.ConversationBehaviorListener {

    private static final String TAG = RongCloudEvent.class.getSimpleName();

    private static RongCloudEvent mRongCloudInstance;

    private Context mContext;

    public RongCloudEvent(Context context) {
        this.mContext = context;
        registerDefaultListener();
    }

    private void registerDefaultListener() {
        RongIM.setConversationBehaviorListener(this);
        InputProvider.ExtendProvider[] provider = {
                new ImageInputProvider(RongContext.getInstance()),//图片
                new CameraInputProvider(RongContext.getInstance()),//相机
                new VoIPInputProvider(RongContext.getInstance())// 语音通话
        };
        RongIM.getInstance().resetInputExtensionProvider(Conversation.ConversationType.PRIVATE,
                provider);
        RongIM.getInstance().resetInputExtensionProvider(Conversation.ConversationType
                .PUBLIC_SERVICE, provider);
        RongIM.getInstance().resetInputExtensionProvider(Conversation.ConversationType
                .DISCUSSION, provider);
        RongIM.getInstance().resetInputExtensionProvider(Conversation.ConversationType
                .SYSTEM, provider);
    }

    /**
     * 初始化 RongCloud.
     *
     * @param context 上下文。
     */
    public static void init(Context context) {

        if (mRongCloudInstance == null) {

            synchronized (RongCloudEvent.class) {

                if (mRongCloudInstance == null) {
                    mRongCloudInstance = new RongCloudEvent(context);
                }
            }
        }
    }


    @Override
    public boolean onUserPortraitClick(Context context, Conversation.ConversationType
            conversationType, UserInfo userInfo) {
        return false;
    }

    @Override
    public boolean onUserPortraitLongClick(Context context, Conversation.ConversationType
            conversationType, UserInfo userInfo) {
        return false;
    }

    @Override
    public boolean onMessageClick(Context context, View view, Message message) {
        if (message.getContent() instanceof ImageMessage) {
            ImageMessage imageMessage = (ImageMessage) message.getContent();
            Intent intent = new Intent(context, PhotoActivity.class);
            intent.putExtra("photo", imageMessage.getLocalUri() == null ? imageMessage
                    .getRemoteUri() : imageMessage.getLocalUri());
            context.startActivity(intent);
            return true;
        }
        return false;
    }

    @Override
    public boolean onMessageLinkClick(Context context, String s) {
        return false;
    }

    @Override
    public boolean onMessageLongClick(Context context, View view, Message message) {
        return false;
    }
}
