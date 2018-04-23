package com.hazelcast.springboot.caching.member;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=HzClusterMember.class, loader=SpringApplicationContextLoader.class)
@ActiveProfiles("integration")
abstract public class IntegrationBaseTest {
}
