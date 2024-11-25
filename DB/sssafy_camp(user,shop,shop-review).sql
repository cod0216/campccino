-- tbl_user 테이블에 사용자 데이터 삽입
INSERT INTO tbl_user (
    user_id,
    user_password,
    user_phone,
    user_age,
    user_email,
    user_gender,
    user_mileage,
    user_url,
    user_temp_auth,
    user_refresh_token,
    user_grade
) VALUES
('user001', 'password1', '010-1234-5678', 25, 'user001@example.com', 'Male', 1000, 'http://user001.example.com', 'auth001', 'refreshToken001', 'Bronze'),
('user002', 'password2', '010-2345-6789', 30, 'user002@example.com', 'Female', 1500, 'http://user002.example.com', 'auth002', 'refreshToken002', 'Silver'),
('user003', 'password3', '010-3456-7890', 22, 'user003@example.com', 'Male', 2000, 'http://user003.example.com', 'auth003', 'refreshToken003', 'Gold'),
('user004', 'password4', '010-4567-8901', 28, 'user004@example.com', 'Female', 2500, 'http://user004.example.com', 'auth004', 'refreshToken004', 'Platinum'),
('user005', 'password5', '010-5678-9012', 35, 'user005@example.com', 'Male', 3000, 'http://user005.example.com', 'auth005', 'refreshToken005', 'Diamond'),
('user006', 'password6', '010-6789-0123', 27, 'user006@example.com', 'Female', 1800, 'http://user006.example.com', 'auth006', 'refreshToken006', 'Silver'),
('user007', 'password7', '010-7890-1234', 24, 'user007@example.com', 'Male', 1200, 'http://user007.example.com', 'auth007', 'refreshToken007', 'Bronze'),
('user008', 'password8', '010-8901-2345', 32, 'user008@example.com', 'Female', 2200, 'http://user008.example.com', 'auth008', 'refreshToken008', 'Gold'),
('user009', 'password9', '010-9012-3456', 29, 'user009@example.com', 'Male', 2700, 'http://user009.example.com', 'auth009', 'refreshToken009', 'Platinum'),
('user010', 'password10', '010-0123-4567', 26, 'user010@example.com', 'Female', 1600, 'http://user010.example.com', 'auth010', 'refreshToken010', 'Silver');

INSERT INTO tbl_camp_product (
    product_name,
    product_category
) VALUES
('Tent Pro', 1),
('Sleeping Bag Ultra', 2),
('Camping Stove', 3),
('Hiking Backpack', 4),
('Portable Chair', 5),
('Camping Lantern', 6),
('First Aid Kit', 7),
('Multi-tool', 8),
('Cooler Box', 9),
('Portable Grill', 10);


INSERT INTO tbl_camp_shop (
    shop_title,
    shop_date,
    shop_view_count,
    shop_status,
    shop_img,
    shop_price,
    shop_comment,
    shop_rating,
    Field,
    product_id,
    user_id
) VALUES
-- 상점 1 ~ 10
('캠핑 필수품', NOW(), 150, 'Active', 'https://via.placeholder.com/150?text=Store+1', 50000, '모든 캠핑 용품을 한곳에서.', 4.5, '텐트', 1, 'user001'),
('자연의 허브', NOW(), 200, 'Active', 'https://via.placeholder.com/150?text=Store+2', 75000, '모험을 위한 품질 좋은 장비.', 4.2, '조리기구', 2, 'user002'),
('트레일 블레이저', NOW(), 180, 'Active', 'https://via.placeholder.com/150?text=Store+3', 60000, '트레일을 위한 장비를 준비하세요.', 4.8, '조리기구', 3, 'user003'),
('피크 퍼포먼스', NOW(), 220, 'Active', 'https://via.placeholder.com/150?text=Store+4', 85000, '최고의 성능을 위한 장비.', 4.6, '조리기구', 4, 'user004'),
('캠프 컴포트', NOW(), 160, 'Active', 'https://via.placeholder.com/150?text=Store+5', 55000, '편안한 캠핑 솔루션.', 4.3, '의자', 5, 'user005'),
('어드벤처 기어', NOW(), 190, 'Active', 'https://via.placeholder.com/150?text=Store+6', 70000, '모험을 위한 최고의 장비.', 4.7, '조리기구', 6, 'user006'),
('아웃도어 오아시스', NOW(), 170, 'Active', 'https://via.placeholder.com/150?text=Store+7', 65000, '자연 속의 오아시스.', 4.4, '조리기구', 7, 'user007'),
('하이커의 헤이븐', NOW(), 210, 'Active', 'https://via.placeholder.com/150?text=Store+8', 80000, '모든 하이커를 위한 헤이븐.', 4.9, '조리기구', 8, 'user008'),
('트레일 마스터스', NOW(), 140, 'Active', 'https://via.placeholder.com/150?text=Store+9', 50000, '장비로 트레일을 정복하세요.', 4.1, '조리기구', 9, 'user009'),
('캠프파이어 코너', NOW(), 130, 'Active', 'https://via.placeholder.com/150?text=Store+10', 48000, '캠프파이어를 위한 모든 것.', 4.0, '조리기구', 10, 'user010'),

-- 상점 11 ~ 20
('마운틴 피크', NOW(), 155, 'Active', 'https://via.placeholder.com/150?text=Store+11', 52000, '산 정상까지의 여정을 위한 장비.', 4.3, '텐트', 1, 'user001'),
('리버 리트리트', NOW(), 165, 'Active', 'https://via.placeholder.com/150?text=Store+12', 53000, '강가에서의 휴식을 위한 제품.', 4.2, '조리기구', 2, 'user002'),
('포레스트 파인더스', NOW(), 175, 'Active', 'https://via.placeholder.com/150?text=Store+13', 54000, '숲 속을 길잡이해주는 장비.', 4.5, '조리기구', 3, 'user003'),
('데저트 덴웰러스', NOW(), 185, 'Active', 'https://via.placeholder.com/150?text=Store+14', 56000, '사막 생존을 위한 장비.', 4.1, '조리기구', 4, 'user004'),
('레이크사이드 리빙', NOW(), 195, 'Active', 'https://via.placeholder.com/150?text=Store+15', 57000, '호숫가에서의 편안한 생활.', 4.4, '조리기구', 5, 'user005'),
('트레일 태클', NOW(), 205, 'Active', 'https://via.placeholder.com/150?text=Store+16', 58000, '모든 트레일을 위한 장비.', 4.6, '조리기구', 6, 'user006'),
('서밋 서플라이즈', NOW(), 215, 'Active', 'https://via.placeholder.com/150?text=Store+17', 59000, '정상 탐험을 위한 서플라이.', 4.7, '조리기구', 7, 'user007'),
('와일드니스 웨어하우스', NOW(), 225, 'Active', 'https://via.placeholder.com/150?text=Store+18', 60000, '야생의 필수품을 위한 창고.', 4.8, '조리기구', 8, 'user008'),
('캠핑 센트럴', NOW(), 235, 'Active', 'https://via.placeholder.com/150?text=Store+19', 61000, '모든 캠핑 필요를 위한 중심지.', 4.9, '조리기구', 9, 'user009'),
('아웃도어 아웃피터스', NOW(), 245, 'Active', 'https://via.placeholder.com/150?text=Store+20', 62000, '야외 모험을 위한 장비.', 4.2, '조리기구', 10, 'user010'),

-- 상점 21 ~ 30
('에코 캠핑', NOW(), 150, 'Active', 'https://via.placeholder.com/150?text=Store+21', 50500, '친환경 캠핑 솔루션.', 4.3, '텐트', 1, 'user001'),
('트레일 트레저스', NOW(), 160, 'Active', 'https://via.placeholder.com/150?text=Store+22', 51500, '트레일에서 보물을 찾으세요.', 4.4, '조리기구', 2, 'user002'),
('포레스트 포지', NOW(), 170, 'Active', 'https://via.placeholder.com/150?text=Store+23', 52500, '숲 속 길을 닦아주는 장비.', 4.1, '조리기구', 3, 'user003'),
('데저트 다이나믹스', NOW(), 180, 'Active', 'https://via.placeholder.com/150?text=Store+24', 53500, '사막 캠핑을 위한 다이나믹한 솔루션.', 4.0, '조리기구', 4, 'user004'),
('레이크사이드 럭셔리즈', NOW(), 190, 'Active', 'https://via.placeholder.com/150?text=Store+25', 54500, '호숫가에서의 럭셔리 캠핑.', 4.5, '조리기구', 5, 'user005'),
('트레일 트라이엄프', NOW(), 200, 'Active', 'https://via.placeholder.com/150?text=Store+26', 55500, '트레일에서의 성공.', 4.6, '조리기구', 6, 'user006'),
('서밋 섹세스', NOW(), 210, 'Active', 'https://via.placeholder.com/150?text=Store+27', 56500, '모든 정상 탐험의 성공을 위한 장비.', 4.7, '조리기구', 7, 'user007'),
('와일드니스 원더스', NOW(), 220, 'Active', 'https://via.placeholder.com/150?text=Store+28', 57500, '야생의 경이로움을 느껴보세요.', 4.8, '조리기구', 8, 'user008'),
('캠핑 코브', NOW(), 230, 'Active', 'https://via.placeholder.com/150?text=Store+29', 58500, '캠핑 필수품을 위한 코브.', 4.9, '조리기구', 9, 'user009'),
('아웃도어 오아시스', NOW(), 240, 'Active', 'https://via.placeholder.com/150?text=Store+30', 59500, '모든 야외 필요를 위한 오아시스.', 4.2, '조리기구', 10, 'user010'),

-- 상점 31 ~ 40
('마운틴 마켓', NOW(), 145, 'Active', 'https://via.placeholder.com/150?text=Store+31', 50000, '산 모험을 위한 마켓.', 4.3, '텐트', 1, 'user001'),
('리버 랜데브뢰', NOW(), 155, 'Active', 'https://via.placeholder.com/150?text=Store+32', 51000, '강가에서의 만남.', 4.4, '조리기구', 2, 'user002'),
('포레스트 프론티어', NOW(), 165, 'Active', 'https://via.placeholder.com/150?text=Store+33', 52000, '숲 속의 최전선.', 4.1, '조리기구', 3, 'user003'),
('데저트 도메인', NOW(), 175, 'Active', 'https://via.placeholder.com/150?text=Store+34', 53000, '사막 장비의 도메인.', 4.0, '조리기구', 4, 'user004'),
('레이크사이드 라이프', NOW(), 185, 'Active', 'https://via.placeholder.com/150?text=Store+35', 54000, '호숫가에서의 삶.', 4.5, '조리기구', 5, 'user005'),
('트레일 트렉', NOW(), 195, 'Active', 'https://via.placeholder.com/150?text=Store+36', 55000, '트레일을 함께 트렉하세요.', 4.6, '조리기구', 6, 'user006'),
('서밋 스피어', NOW(), 205, 'Active', 'https://via.placeholder.com/150?text=Store+37', 56000, '서밋 서플라이즈의 영역.', 4.7, '조리기구', 7, 'user007'),
('와일드니스 윈드', NOW(), 215, 'Active', 'https://via.placeholder.com/150?text=Store+38', 57000, '야생 속의 바람.', 4.8, '조리기구', 8, 'user008'),
('캠핑 센트럴', NOW(), 225, 'Active', 'https://via.placeholder.com/150?text=Store+39', 58000, '캠핑의 중심지.', 4.9, '조리기구', 9, 'user009'),
('아웃도어 오리진', NOW(), 235, 'Active', 'https://via.placeholder.com/150?text=Store+40', 59000, '야외의 기원을 위한 장비.', 4.2, '조리기구', 10, 'user010'),

-- 상점 41 ~ 50
('에코 트레일', NOW(), 148, 'Active', 'https://via.placeholder.com/150?text=Store+41', 50200, '친환경 트레일 장비.', 4.3, '텐트', 1, 'user001'),
('네이처 넥서스', NOW(), 158, 'Active', 'https://via.placeholder.com/150?text=Store+42', 51200, '자연 공급의 넥서스.', 4.4, '조리기구', 2, 'user002'),
('포레스트 퓨전', NOW(), 168, 'Active', 'https://via.placeholder.com/150?text=Store+43', 52200, '숲 속 장비의 융합.', 4.1, '조리기구', 3, 'user003'),
('데저트 드리프트', NOW(), 178, 'Active', 'https://via.placeholder.com/150?text=Store+44', 53200, '사막을 드리프트하세요.', 4.0, '조리기구', 4, 'user004'),
('레이크사이드 레전드', NOW(), 188, 'Active', 'https://via.placeholder.com/150?text=Store+45', 54200, '호숫가의 전설.', 4.5, '조리기구', 5, 'user005'),
('트레일 트렉', NOW(), 198, 'Active', 'https://via.placeholder.com/150?text=Store+46', 55200, '트레일과 함께 트렉하세요.', 4.6, '조리기구', 6, 'user006'),
('서밋 스피어', NOW(), 208, 'Active', 'https://via.placeholder.com/150?text=Store+47', 56200, '서밋 서플라이즈의 스피어.', 4.7, '조리기구', 7, 'user007'),
('와일드니스 윈드', NOW(), 218, 'Active', 'https://via.placeholder.com/150?text=Store+48', 57200, '야생의 바람.', 4.8, '조리기구', 8, 'user008'),
('캠핑 컴파스', NOW(), 228, 'Active', 'https://via.placeholder.com/150?text=Store+49', 58200, '캠핑 모험을 위한 컴파스.', 4.9, '조리기구', 9, 'user009'),
('아웃도어 오아시스', NOW(), 238, 'Active', 'https://via.placeholder.com/150?text=Store+50', 59200, '모든 야외 필요를 위한 오아시스.', 4.2, '조리기구', 10, 'user010');



insert INTO tbl_shop_review (shop_id, user_id, shop_rate, comment) VALUES
    -- shop_id 1
    (1, 'user001', 5, '정말 훌륭한 제품입니다!'),
    (1, 'user002', 4, '만족스러운 구매였어요.'),
    (1, 'user003', 3, '보통이에요. 기대보다는 못 미쳤습니다.'),
    (1, 'user004', 4, '가성비가 좋습니다. 다시 구매할 의향 있어요.'),
    
    -- shop_id 2
    (2, 'user005', 5, '최고의 품질! 추천합니다.'),
    (2, 'user006', 4, '기대 이상이에요. 잘 쓰고 있습니다.'),
    (2, 'user007', 2, '별로 만족스럽지 않아요. 개선이 필요합니다.'),
    (2, 'user008', 3, '평균적인 성능입니다.'),
    
    -- shop_id 3
    (3, 'user009', 4, '좋은 제품이에요. 만족합니다.'),
    (3, 'user010', 5, '완벽해요! 매우 만족합니다.'),
    (3, 'user001', 3, '그럭저럭입니다.'),
    (3, 'user002', 4, '추천할 만해요.'),
    
    -- shop_id 4
    (4, 'user003', 5, '놀라운 성능! 최고예요.'),
    (4, 'user004', 4, '만족스럽습니다.'),
    (4, 'user005', 3, '평균적인 제품입니다.'),
    (4, 'user006', 4, '잘 사용하고 있습니다.'),
    
    -- shop_id 5
    (5, 'user007', 5, '아주 좋습니다!'),
    (5, 'user008', 4, '기대 이상이에요.'),
    (5, 'user009', 3, '보통입니다.'),
    (5, 'user010', 4, '재구매 의향 있습니다.');
