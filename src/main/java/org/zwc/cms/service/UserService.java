package org.zwc.cms.service;


import java.util.List;

import org.zwc.cms.bean.User;


public interface UserService {
    
    /**
     * ���ݽӿڲ�ѯ���õ��û�
     */
    public List<User> findAllUser();

    /**
     * �����û���id����ѯ�û�����Ϣ
     * @param i
     * @return
     */
	public User getUserInfoByUserId(int i);
}