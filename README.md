# SnakeGame_1.0
Giới thiệu: Game rắn săn mồi là một tựa game cổ điển đã từng rất thành công ở những năm 9x 2000.

1. Các chức năng chính:
- Di chuyển
- Tạo thức ăn
- Rắn dài ra khi ăn thức ăn
- Rắn cắn thân hoặc đuôi thì gameover
2. Rắn
- Rắn khi khởi tạo có đô dài là 3, đơn vị là 1 ô vuông, khi ăn mồi sẽ dài ra 1 đơn vị
- Rắn di chuyển theo hướng mũi tên(không thể di chuyển theo hướng ngược lại so với hướng đang di chuyển)
- Rắn cắn thân hoặc đuôi thì gameover
- Rắn có thể di xuyên màn hình và xuất hiện ở màn hình bên kia
- Rắn ăn mồi to sẽ không dài ra
3. Mồi
- Xuất hiện ngẫu nhiên khác tọa độ của rắn
- Đối với mồi to, sau khi ăn 1 số lượng mồi nhỏ nhất định sẽ xuất hiện mồi to, lần sau xuất hiện phải ăn số mồi nhỏ trước đó +3. Sau khi rắn đi 20 ô mà không ăn được mồi to, mồi to sẽ biến mất.
4. User
- Tên: sau khi game over sẽ cho người chơi nhập tên.
- Điểm: số điểm của người chơi sau khi gameover
- Tên và điểm sẽ được lưu lại tại 1 file.txt
5. Tính điểm
- Ở Level A mỗi lần ăn sẽ được cộng: Ax100 điểm
- Khi ăn được mồi to sẽ được số điểm: số Level x 1000 điểm
- Điểm sẽ được lưu vào trong file(.txt)
- Trên màn hình hiển thị chỉ hiện top 5 người chơi có số điểm cao nhất
6. Level
- Sau khi đạt đến chiều dài X nào đó sẽ tăng Level lên: Y
- Rắn trở về trạng thái như đã khởi tạo, tốc độ di chuyển tăng lên
- Sau khi đạt đến chiều dài X+a lại tăng Level lên Y+1
7. Giao Diện
- Sử dụng lớp Jframe và Jpanel để tạo cửa sổ và giao điện

------------------------------------------------------------------------------------------
  Qua quá trình làm đồ án hiểu được cơ bản ngôn ngữ java cùng các tính chất của lập trinh hướng đối tượng, ngoài ra còn biết thêm được các kiến thức về java.swing để tạo cửa sổ và thiết kế giao diện. Trong tương lai game có thể phát triển với level khó hơn như tạo tường, vật cản để rắn khó di chuyển hơn, khi đó game sẽ trở nên thú vị hơn.


 

