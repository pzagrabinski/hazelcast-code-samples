package com.hazelcast.springboot.caching.member;

import com.hazelcast.core.Cluster;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.nio.Address;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class HzClusterMemberTest extends IntegrationBaseTest {

    @Autowired
    HazelcastInstance hazelcastInstance;

    @Test
    public void testClusterMembership() {
        //given
        Cluster cluster = hazelcastInstance.getCluster();

        //when
        Address address = cluster.getLocalMember().getAddress();

        //then
        assertThat(address).isNotNull();

    }

}