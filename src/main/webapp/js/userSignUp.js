let isIdAvailable = false; // 아이디 중복 확인 상태

function checkId() {
    const userId = document.getElementById('user_id').value.trim();
    const idCheckMessage = document.getElementById('idCheckMessage');

    if (userId === '') {
        idCheckMessage.style.color = 'red';
        idCheckMessage.textContent = '아이디를 입력해주세요.';
        isIdAvailable = false;
        return;
    }

    fetch(`duplicatedIdCheckForm.user?userId=${userId}`, {
        method: 'POST',
    })
    .then((response) => {
        if (!response.ok) {
            throw new Error('중복 확인 서버 응답 실패');
        }
        return response.text(); // 서버에서 int 값만 반환하므로 텍스트로 처리
    })
    .then((checkCnt) => {
        const count = parseInt(checkCnt, 10); // 반환된 값을 정수로 변환
        if (count > 0) {
            idCheckMessage.style.color = 'red';
            idCheckMessage.textContent = '중복된 아이디입니다.';
            isIdAvailable = false;
        } else if (count === 0) {
            idCheckMessage.style.color = 'green';
            idCheckMessage.textContent = '사용 가능한 아이디입니다.';
            isIdAvailable = true;
        } else {
            idCheckMessage.style.color = 'red';
            idCheckMessage.textContent = 'ID 중복 확인 중 서버 오류가 발생했습니다.';
            isIdAvailable = false;
        }
    })
    .catch((error) => {
        console.error('중복 확인 요청 중 오류 발생:', error);
        idCheckMessage.style.color = 'red';
        idCheckMessage.textContent = '중복 확인 중 오류가 발생했습니다.';
        isIdAvailable = false;
    });
}

// 폼 제출 시 중복 확인 상태 검증
document.querySelector('form').addEventListener('submit', function (event) {
    if (!isIdAvailable) {
        event.preventDefault(); // 폼 제출 중단
        alert('아이디 중복 확인을 완료해주세요.');
    }
});
